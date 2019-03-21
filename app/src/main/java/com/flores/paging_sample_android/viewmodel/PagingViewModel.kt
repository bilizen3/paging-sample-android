package com.flores.paging_sample_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.flores.paging_sample_android.data.model.ResultsItem
import com.flores.paging_sample_android.datasource.FeedDataSource
import com.flores.paging_sample_android.datasource.FeedPageKeyedDataSource
import com.flores.paging_sample_android.utils.NetworkState
import java.security.Key
import java.util.concurrent.Executors

class PagingViewModel : ViewModel() {


    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(true)
        .setInitialLoadSizeHint(20)
        .setPageSize(20)
        .build()


    var searchLiveData= MutableLiveData<LivePagedListBuilder<*,*>>()

    private var itemLiveData= Transformations
        .switchMap(searchLiveData){
                it->search()
    }

    fun getItemLiveData() = itemLiveData


    private fun search()= (LivePagedListBuilder(FeedDataSource(), pagedListConfig)).
            setFetchExecutor(Executors.newFixedThreadPool(3))
            .build()

    fun searchRepo(queryString: String) {
        searchLiveData.postValue(LivePagedListBuilder(FeedDataSource(), pagedListConfig))
    }
}