package com.flores.paging_sample_android.data.model

data class SearchResultResponse(val page: Int = 0,
                                val totalPages: Int = 0,
                                val results: List<ResultsItem>?,
                                val totalResults: Int = 0)