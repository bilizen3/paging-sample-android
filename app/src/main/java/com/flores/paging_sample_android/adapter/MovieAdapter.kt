package com.flores.paging_sample_android.adapter

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.flores.paging_sample_android.data.local.entity.Movie

class MovieAdapter : PagedListAdapter<Movie, MovieAdapter.MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
       return MovieViewHolder(parent.)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}