package com.flores.paging_sample_android.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.flores.paging_sample_android.R
import com.flores.paging_sample_android.adapter.MovieAdapter
import com.flores.paging_sample_android.repository.PagingRepository
import com.flores.paging_sample_android.utils.NetworkState
import com.flores.paging_sample_android.viewmodel.PagingViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var pagingViewModel: PagingViewModel
    private var movieAdapter = MovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        btnSearch.setOnClickListener {
            pagingViewModel.searchMovie("")
        }
    }

    private fun init() {
        pagingViewModel = PagingViewModel(PagingRepository())
        rvMovie.adapter = movieAdapter
        pagingViewModel.resultItems.observe(this, Observer { movieList ->
            movieAdapter.submitList(movieList)
        })

        pagingViewModel.resultCountTotal.observe(this, Observer {
            tvTotal.text = it.toString()
        })

        pagingViewModel.status.observe(this, Observer { status ->
            movieAdapter.setNetworkState(NetworkState(status))
        })

    }

}
