package com.flores.paging_sample_android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.flores.paging_sample_android.R
import com.flores.paging_sample_android.data.model.ResultsItem
import com.flores.paging_sample_android.utils.NetworkState
import com.flores.paging_sample_android.utils.Status
import kotlinx.android.synthetic.main.item_loading.view.*
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter : PagedListAdapter<ResultsItem, RecyclerView.ViewHolder>(diffCallback) {

    private var networkState: NetworkState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (TYPE_PROGRESS == viewType) {
            LoadingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false))
        } else {
            ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            holder.bind(getItem(position)!!)
        } else {
            (holder as LoadingViewHolder).bind(networkState!!)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            TYPE_PROGRESS
        } else {
            TYPE_ITEM
        }
    }

    private fun hasExtraRow(): Boolean {
        return (networkState != null && networkState!!.status != Status.SUCCESS)
    }

    fun setNetworkState(newNetworkState: NetworkState) {
        val previousState = this.networkState
        val previousExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val newExtraRow = hasExtraRow()
        if (previousExtraRow != newExtraRow) {
            if (previousExtraRow) {
                notifyItemRemoved(itemCount)
            } else {
                notifyItemInserted(itemCount)
            }
        } else if (newExtraRow && previousState !== newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(resultsItem: ResultsItem) {
            itemView.movieId.text = resultsItem.id.toString()
            itemView.movieName.text = resultsItem.title
        }
    }

    inner class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(networkState: NetworkState) {
            if (networkState != null && networkState.status == Status.LOADING) {
                itemView.pbLoading.visibility = View.VISIBLE
            } else {
                itemView.pbLoading.visibility = View.GONE
            }
            if (networkState.status == Status.FAILED) {
                //show error
            }
        }
    }

    companion object {
        const val TYPE_PROGRESS = 0
        const val TYPE_ITEM = 1

        private val diffCallback = object : DiffUtil.ItemCallback<ResultsItem>() {
            override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean =
                oldItem == newItem
        }
    }

}