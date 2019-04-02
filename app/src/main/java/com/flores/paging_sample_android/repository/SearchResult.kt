package com.flores.paging_sample_android.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.flores.paging_sample_android.data.model.ResultsItem
import com.flores.paging_sample_android.utils.Status

data class SearchResult(
    val results: LiveData<PagedList<ResultsItem>>,
    val countTotal: LiveData<Int>,
    val status: LiveData<Status>
)