package com.flores.paging_sample_android.datasource

import androidx.paging.PageKeyedDataSource
import com.flores.paging_sample_android.data.model.ResultsItem
import com.flores.paging_sample_android.data.model.SearchResultResponse
import com.flores.paging_sample_android.data.remoto.api.RetrofitClient
import com.flores.paging_sample_android.utils.API_KEY
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedPageKeyedDataSource : PageKeyedDataSource<Int, ResultsItem>() {


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ResultsItem>
    ) {

        RetrofitClient.retrofit.fetchFeed(1, API_KEY)
            .enqueue(object : Callback<SearchResultResponse> {
                override fun onResponse(call: Call<SearchResultResponse>, response: Response<SearchResultResponse>) {
                    if (response.isSuccessful) {
                        callback.onResult(response.body()!!.results!!, null, 2)

                    } else {
                    }
                }

                override fun onFailure(call: Call<SearchResultResponse>, t: Throwable) {
                }
            })


    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ResultsItem>) {
        RetrofitClient.retrofit.fetchFeed(params.key, API_KEY).enqueue(object : Callback<SearchResultResponse> {
            override fun onResponse(call: Call<SearchResultResponse>, response: Response<SearchResultResponse>) {
                if (response.isSuccessful) {
                    val nextKey = if (params.key == response.body()!!.totalResults) null else params.key + 1
                    callback.onResult(response.body()!!.results!!, nextKey)

                } else {
                }
            }

            override fun onFailure(call: Call<SearchResultResponse>, t: Throwable) {
            }
        })

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ResultsItem>) {

    }

}