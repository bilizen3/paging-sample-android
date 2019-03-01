package com.flores.paging_sample_android.repository

import androidx.paging.DataSource
import com.flores.paging_sample_android.data.local.dao.MovieDao
import com.flores.paging_sample_android.data.local.entity.Movie

class PagingRepository(private val movieDao: MovieDao){
    fun allMovies(): DataSource.Factory<Int, Movie> {
        return movieDao.allMovies()
    }

}