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

    var livedataSourceThis= MutableLiveData<FeedDataSource>()

    var countTotal2= Transformations.switchMap(
        livedataSourceThis){
        it.getfeedPageKeyedDataSource()
    }
    var countTotal3= Transformations.switchMap(
        countTotal2){
        it.getNetworkState()
    }

    var countTotal4= Transformations.switchMap(
        livedataSourceThis){
        search(it)
    }

    fun searchNew(){
        livedataSourceThis.postValue(dataSourceThis)
    }

    fun search(feedDataSource: FeedDataSource): LiveData<PagedList<ResultsItem>> {
        return (LivePagedListBuilder(feedDataSource, pagedListConfig))
            .setFetchExecutor(Executors.newFixedThreadPool(3))
            .build()
    }

}