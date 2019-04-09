package com.flores.paging_sample_android.viewmodel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.flores.paging_sample_android.repository.PagingRepository

class PagingViewModel(
    private val repository: PagingRepository
) : ViewModel() {

    private val searchtext = MutableLiveData<String>()

    private var searchTotalLiveData =
        Transformations.map(searchtext) {
            repository.searchTotal(it)
        }

    val resultCountTotal = Transformations.switchMap(searchTotalLiveData) {
        it.countTotal
    }

    val resultItems = Transformations.switchMap(searchTotalLiveData) {
        it.results
    }

    val status = Transformations.switchMap(searchTotalLiveData) {
        it.status
    }

    fun searchMovie(text: String) {
        searchtext.postValue(text)
    }

}