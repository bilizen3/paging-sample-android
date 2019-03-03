package com.flores.paging_sample_android.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.flores.paging_sample_android.data.local.dao.MovieDao
import com.flores.paging_sample_android.data.local.entity.Movie

@Database(
    entities = [Movie::class],
    version = 1,
    exportSchema =false)
abstract class DataBase: RoomDatabase() {
    abstract fun movieDao():MovieDao

    companion object {
        @Volatile
        private var INSTANCE: DataBase? = null
        fun getInstance(context: Context): DataBase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildMoviesDatabase(context).also { INSTANCE = it }
        }
        private fun buildMoviesDatabase(context: Context) = Room.databaseBuilder(context, DataBase::class.java, "movieData").build()
    }

}