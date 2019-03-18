package com.flores.paging_sample_android.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.flores.paging_sample_android.data.model.ResultsItem

class FeedDataSource : DataSource.Factory<Int, ResultsItem>() {

    private val notesLiveData = MutableLiveData<FeedPageKeyedDataSource>()
    private lateinit var feedPageKeyedDataSource: FeedPageKeyedDataSource

    override fun create(): DataSource<Int, ResultsItem> {
        feedPageKeyedDataSource = FeedPageKeyedDataSource()
        notesLiveData.postValue(feedPageKeyedDataSource)
        return feedPageKeyedDataSource
    }


    fun getMutableLiveData(): MutableLiveData<FeedPageKeyedDataSource> {
        return notesLiveData
    }

}