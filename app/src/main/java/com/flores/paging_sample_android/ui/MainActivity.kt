package com.flores.paging_sample_android.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
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

    private lateinit var database: DataBase
    private lateinit var pagingRepository: PagingRepository
    private lateinit var pagingViewModel: PagingViewModel
    private var movieAdapter = MovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        database = DataBase.getInstance(applicationContext)
        pagingRepository = PagingRepository(database.movieDao())
        pagingViewModel = PagingViewModel(pagingRepository)
        insertData()
        rvMovie.layoutManager = LinearLayoutManager(applicationContext)
        rvMovie.adapter = movieAdapter

        pagingViewModel.concertList.observe(this, Observer { movieList ->
            showData(movieList)
        })
    }

    private fun insertData() {
        val movies = mutableListOf<Movie>()
        for (index in 1..100) {
            movies.add(Movie(title = "nuevo$index"))
        }
        pagingRepository.insertMovie(movies)
    }

    private fun showData(movieList: PagedList<Movie>) {
        movieAdapter.submitList(movieList)
    }


}
