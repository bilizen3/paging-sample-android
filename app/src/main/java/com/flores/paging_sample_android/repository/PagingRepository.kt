package com.flores.paging_sample_android.repository

import androidx.paging.DataSource
import com.flores.paging_sample_android.data.local.dao.MovieDao
import com.flores.paging_sample_android.data.local.entity.Movie
import com.flores.paging_sample_android.utils.ioThread

class PagingRepository(private val movieDao: MovieDao) {

    fun allMovies(): DataSource.Factory<Int, Movie> {
        return movieDao.allMovies()
    }

    fun insertMovie(movieList: List<Movie>) {
        ioThread {
            if (movieDao.countMovies() == 0) movieDao.insert(movieList)
        }
    }

}