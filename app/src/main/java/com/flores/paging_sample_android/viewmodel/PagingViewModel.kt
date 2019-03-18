package com.flores.paging_sample_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.flores.paging_sample_android.data.model.ResultsItem
import com.flores.paging_sample_android.datasource.FeedDataSource
import com.flores.paging_sample_android.utils.NetworkState
import java.util.concurrent.Executors

class PagingViewModel : ViewModel() {

    private val networkState: LiveData<NetworkState>

    private var itemLiveData: LiveData<PagedList<ResultsItem>>

    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(true)
        .setInitialLoadSizeHint(30)
        .setPageSize(10)
        .build()

    init {
        val feedDataSource = FeedDataSource()
        networkState = Transformations
            .switchMap(feedDataSource.getMutableLiveData()) { dataSource ->
                dataSource.getNetworkState()
            }
        itemLiveData =
            (LivePagedListBuilder(feedDataSource, pagedListConfig)).setFetchExecutor(Executors.newFixedThreadPool(5))
                .build()
    }

    fun getItemLiveData() = itemLiveData

    fun getNetworkState() = networkState

}