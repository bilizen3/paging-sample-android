package com.flores.paging_sample_android.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.flores.paging_sample_android.R
import com.flores.paging_sample_android.adapter.MovieAdapter
import com.flores.paging_sample_android.data.local.DataBase
import com.flores.paging_sample_android.data.local.entity.Movie
import com.flores.paging_sample_android.repository.PagingRepository
import com.flores.paging_sample_android.viewmodel.PagingViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var database: DataBase
    lateinit var pagingRepository: PagingRepository
    lateinit var pagingViewModel: PagingViewModel
    lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUI()

        database = DataBase.getInstance(applicationContext)!!
        pagingRepository = PagingRepository(database.movieDao())
        pagingViewModel = PagingViewModel(pagingRepository)
        pagingViewModel.concertList.observe(this, Observer { movieList ->
            showData(movieList)
        })
    }

    fun showData(movieList: PagedList<Movie>) {
        movieAdapter.submitList(movieList)
    }

    fun setUI() {
        rvMovie.layoutManager = LinearLayoutManager(applicationContext)
        rvMovie.adapter =movieAdapter
    }

}
