package com.flores.paging_sample_android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.flores.paging_sample_android.datasource.FeedDataSource
import java.util.concurrent.Executors

class PagingViewModel : ViewModel() {


    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(true)
        .setInitialLoadSizeHint(20)
        .setPageSize(20)
        .build()

    var dataSource = MutableLiveData<FeedDataSource>()


    private var itemLiveData = Transformations
        .switchMap(dataSource) { it ->
            search(it)
        }

    fun getItemLiveData() = itemLiveData


    private fun search(feedDataSource: FeedDataSource) =
        (LivePagedListBuilder(feedDataSource, pagedListConfig)).setFetchExecutor(Executors.newFixedThreadPool(3))
            .build()

    fun searchRepo(queryString: String) {
        dataSource.postValue(FeedDataSource())
    }
}