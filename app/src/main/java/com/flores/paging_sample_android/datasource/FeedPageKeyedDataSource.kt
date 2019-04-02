package com.flores.paging_sample_android.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.flores.paging_sample_android.data.model.ResultsItem
import com.flores.paging_sample_android.data.model.SearchResultResponse
import com.flores.paging_sample_android.data.remoto.api.RetrofitClient
import com.flores.paging_sample_android.utils.API_KEY
import com.flores.paging_sample_android.utils.Status
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedPageKeyedDataSource : PageKeyedDataSource<Int, ResultsItem>() {


    private val mutableCountTotal = MutableLiveData<Int>()
    private val mutableStatus = MutableLiveData<Status>()

    fun getNetworkState(): MutableLiveData<Int> {
        return mutableCountTotal
    }

    fun getStatusMutable(): MutableLiveData<Status> {
        return mutableStatus
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ResultsItem>
    ) {
        mutableStatus.postValue(Status.LOADING)
        RetrofitClient.retrofit.fetchFeed(1, API_KEY)
            .enqueue(object : Callback<SearchResultResponse> {
                override fun onResponse(call: Call<SearchResultResponse>, response: Response<SearchResultResponse>) {
                    if (response.isSuccessful) {
                        mutableStatus.postValue(Status.SUCCESS)
                        callback.onResult(response.body()!!.results!!, null, 2)
                        mutableCountTotal.postValue(response.body()!!.totalResults)
                    } else {
                        mutableStatus.postValue(Status.FAILED)
                    }
                }

                override fun onFailure(call: Call<SearchResultResponse>, t: Throwable) {
                    mutableStatus.postValue(Status.FAILED)
                }
            })


    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ResultsItem>) {
        mutableStatus.postValue(Status.LOADING)
        RetrofitClient.retrofit.fetchFeed(params.key, API_KEY).enqueue(object : Callback<SearchResultResponse> {
            override fun onResponse(call: Call<SearchResultResponse>, response: Response<SearchResultResponse>) {
                if (response.isSuccessful) {
                    mutableStatus.postValue(Status.SUCCESS)
                    val nextKey = if (params.key == response.body()!!.totalResults) null else params.key + 1
                    callback.onResult(response.body()!!.results!!, nextKey)
                    mutableCountTotal.postValue(response.body()!!.totalResults)
                } else {
                    mutableStatus.postValue(Status.FAILED)
                }
            }

            override fun onFailure(call: Call<SearchResultResponse>, t: Throwable) {
                mutableStatus.postValue(Status.FAILED)
            }
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ResultsItem>) {

    }

}