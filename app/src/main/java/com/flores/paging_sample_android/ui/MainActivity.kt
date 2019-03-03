package com.flores.paging_sample_android.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.flores.paging_sample_android.R
import com.flores.paging_sample_android.data.local.DataBase
import com.flores.paging_sample_android.data.local.dao.MovieDao
import com.flores.paging_sample_android.data.local.entity.Movie
import com.flores.paging_sample_android.repository.PagingRepository
import com.flores.paging_sample_android.viewmodel.PagingViewModel


class MainActivity : AppCompatActivity() {

    val database = DataBase.getInstance(applicationContext)
    val pagingRepository = PagingRepository(database.movieDao())
    var pagingViewModel = PagingViewModel(pagingRepository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pagingViewModel = ViewModelProviders.of(this).get(PagingViewModel::class.java)
        pagingViewModel.concertList.observe(this, Observer { movieList ->
            showData(movieList)
        })
    }

    fun showData(movieList: PagedList<Movie>) {

    }

}
