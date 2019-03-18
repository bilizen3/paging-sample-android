package com.flores.paging_sample_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.flores.paging_sample_android.data.local.entity.Movie
import com.flores.paging_sample_android.data.model.ResultsItem
import com.flores.paging_sample_android.datasource.FeedDataSource
import com.flores.paging_sample_android.repository.PagingRepository
import com.flores.paging_sample_android.utils.NetworkState
import java.util.concurrent.Executors

class PagingViewModel(pagingRepository: PagingRepository) : ViewModel() {

    private val networkState: LiveData<NetworkState>

    private var itemLiveData: LiveData<PagedList<ResultsItem>>

    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(true)
        .setInitialLoadSizeHint(30)
        .setPageSize(10)
        .build()

    val concertList: LiveData<PagedList<Movie>> =
        LivePagedListBuilder(pagingRepository.allMovies(), pagedListConfig).build()

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