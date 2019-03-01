package com.flores.paging_sample_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.flores.paging_sample_android.data.local.entity.Movie
import com.flores.paging_sample_android.repository.PagingRepository

class PagingViewModel(pagingRepository: PagingRepository) : ViewModel() {

    val concertList : LiveData<PagedList<Movie>> =
        LivePagedListBuilder(pagingRepository.allMovies(),10).build()

}