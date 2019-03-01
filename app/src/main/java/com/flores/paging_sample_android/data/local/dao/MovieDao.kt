package com.flores.paging_sample_android.data.local.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.flores.paging_sample_android.data.local.entity.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movieList: List<Movie>)

    @Query("SELECT * FROM Movie ORDER BY popularity DESC, title ASC")
    fun allMovies(): DataSource.Factory<Int, Movie>
}