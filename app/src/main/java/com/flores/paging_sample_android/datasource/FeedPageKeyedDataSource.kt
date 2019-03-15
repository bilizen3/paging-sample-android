package com.flores.paging_sample_android.datasource

import androidx.lifecycle.LiveData
import androidx.paging.PageKeyedDataSource
import com.flores.paging_sample_android.data.local.entity.Movie

class FeedPageKeyedDataSource: PageKeyedDataSource<Int, Movie>() {

    val networkState = MutableLiveData<Object>()
    val initialLoading = MutableLiveData<Object>()

    override fun loadInitial(params: LoadInitialParams<Int>,
                             callback: LoadInitialCallback<Int,
                                     Movie>) {


    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {

    }
}