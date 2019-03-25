package com.flores.paging_sample_android.repository

import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.flores.paging_sample_android.datasource.FeedDataSource
import java.util.concurrent.Executors

class PagingRepository {

    private val pagedListConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(true)
        .setInitialLoadSizeHint(20)
        .setPageSize(20)
        .build()

    fun searchTotal(query: String): SearchResult {

        val feedDataSource = FeedDataSource()

        val listitem = (LivePagedListBuilder(feedDataSource, pagedListConfig))
            .setFetchExecutor(Executors.newFixedThreadPool(3))
            .build()

        val feedPageKeyedDataSource = Transformations.switchMap(feedDataSource.getfeedPageKeyedDataSource()) {
            it.getNetworkState()
        }

        return SearchResult(listitem, feedPageKeyedDataSource)
    }

}