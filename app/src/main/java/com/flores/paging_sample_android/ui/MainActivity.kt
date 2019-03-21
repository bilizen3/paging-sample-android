package com.flores.paging_sample_android.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.flores.paging_sample_android.R
import com.flores.paging_sample_android.adapter.MovieAdapter
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
            pagingViewModel.searchRepo("")
        }
    }

    private fun init() {
        pagingViewModel = PagingViewModel()
        rvMovie.adapter = movieAdapter
        pagingViewModel.getItemLiveData().observe(this, Observer { movieList ->
                movieAdapter.submitList(movieList)

        })


    }


}
