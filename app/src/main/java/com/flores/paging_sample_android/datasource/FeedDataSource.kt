package com.flores.paging_sample_android.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.flores.paging_sample_android.model.Movie

class FeedDataSource : DataSource.Factory<Int, Movie>() {

    private val notesLiveData = MutableLiveData<FeedPageKeyedDataSource>()


    override fun create(): DataSource<Int, Movie> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}