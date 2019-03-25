package com.flores.paging_sample_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.flores.paging_sample_android.data.model.ResultsItem
import com.flores.paging_sample_android.repository.PagingRepository
import com.flores.paging_sample_android.repository.SearchResult

class PagingViewModel(
    val repository: PagingRepository
) : ViewModel() {

    val searchtext = MutableLiveData<String>()

    var searchTotalLiveData: LiveData<SearchResult> =
        Transformations.map(searchtext) {
            repository.searchTotal(it)
        }

    var resultCountTotal: LiveData<Int> =
        Transformations.switchMap(searchTotalLiveData) {
            it.countTotal
        }

    var resultItems: LiveData<PagedList<ResultsItem>> =
        Transformations.switchMap(searchTotalLiveData) {
            it.results
        }

    fun searchMovie(text: String) {
        searchtext.postValue(text)
    }

}