package com.flores.paging_sample_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.flores.paging_sample_android.data.model.ResultsItem
import com.flores.paging_sample_android.datasource.FeedDataSource
import java.util.concurrent.Executors

class PagingViewModel : ViewModel() {

    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(true)
        .setInitialLoadSizeHint(20)
        .setPageSize(20)
        .build()


    var dataSourceThis = FeedDataSource()

    var dataSource = MutableLiveData<FeedDataSource>()

    var countTotal= Transformations.switchMap(
        dataSourceThis.getfeedPageKeyedDataSource()){
        it.getNetworkState()
    }

    val itemLiveData = search()

    private fun search(): LiveData<PagedList<ResultsItem>> {
        return (LivePagedListBuilder(dataSourceThis, pagedListConfig))
            .setFetchExecutor(Executors.newFixedThreadPool(3))
            .build()
    }

    fun searchRepo(queryString: String) {
        this.dataSourceThis=FeedDataSource()
        dataSource.postValue(dataSourceThis)
    }

}