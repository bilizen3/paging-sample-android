package com.flores.paging_sample_android.data.Model

data class SearchResultResponse(val page: Int = 0,
                                val totalPages: Int = 0,
                                val results: List<ResultsItem>?,
                                val totalResults: Int = 0)