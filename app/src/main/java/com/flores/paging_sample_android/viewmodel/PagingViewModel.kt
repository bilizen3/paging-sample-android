package com.flores.paging_sample_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.flores.paging_sample_android.data.model.ResultsItem
import com.flores.paging_sample_android.repository.PagingRepository
import com.flores.paging_sample_android.repository.SearchResult
import com.flores.paging_sample_android.utils.Status

class PagingViewModel(
    private val repository: PagingRepository
) : ViewModel() {

    private val searchtext = MutableLiveData<String>()

    private var searchTotalLiveData: LiveData<SearchResult> =
        Transformations.map(searchtext) {
            repository.searchTotal(it)
        }

    val resultCountTotal: LiveData<Int> =
        Transformations.switchMap(searchTotalLiveData) {
            it.countTotal
        }

    val resultItems: LiveData<PagedList<ResultsItem>> =
        Transformations.switchMap(searchTotalLiveData) {
            it.results
        }

    val status: LiveData<Status> =
        Transformations.switchMap(searchTotalLiveData) {
            it.status
        }

    fun searchMovie(text: String) {
        searchtext.postValue(text)
    }

}