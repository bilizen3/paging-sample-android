package com.flores.paging_sample_android.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.flores.paging_sample_android.data.model.ResultsItem

data class SearchResult(
    val results: LiveData<PagedList<ResultsItem>>,
    val countTotal: LiveData<Int>
)