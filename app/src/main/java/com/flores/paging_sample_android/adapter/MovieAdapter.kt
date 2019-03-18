package com.flores.paging_sample_android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(TYPE_PROGRESS==viewType){
            LoadingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false))

        }else{
            ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false))

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }


    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: ResultsItem) {
            itemView.movieId.text = movie.title
        }
    }

    inner class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(networkState: NetworkState) {
            if( networkState.status==Status.RUNNING){
                itemView.pbLoading.visibility=View.VISIBLE
            }else{
                itemView.pbLoading.visibility=View.GONE
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