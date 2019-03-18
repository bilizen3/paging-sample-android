package com.flores.paging_sample_android.datasource

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.flores.paging_sample_android.data.model.ResultsItem
import com.flores.paging_sample_android.data.model.SearchResultResponse
import com.flores.paging_sample_android.data.remoto.api.RetrofitClient
import com.flores.paging_sample_android.utils.API_KEY
import com.flores.paging_sample_android.utils.NetworkState
import com.flores.paging_sample_android.utils.Status
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedPageKeyedDataSource : PageKeyedDataSource<Int, ResultsItem>() {
    private var networkState: MutableLiveData<NetworkState> = MutableLiveData<NetworkState>()
    private var initialLoading: MutableLiveData<NetworkState> = MutableLiveData<NetworkState>()

    fun getNetworkState() = networkState

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ResultsItem>
    ) {
        initialLoading.postValue(NetworkState(Status.RUNNING, "cargando"))
        networkState.postValue(NetworkState(Status.RUNNING, "cargando"))

        RetrofitClient.retrofit.fetchFeed(1, API_KEY)
            .enqueue(object : Callback<SearchResultResponse> {
                override fun onResponse(call: Call<SearchResultResponse>, response: Response<SearchResultResponse>) {
                    if (response.isSuccessful) {
                        callback.onResult(response.body()!!.results!!, null, 2)
                        Log.i("aaa","2")
                        initialLoading.postValue(NetworkState(Status.SUCCESS))
                        networkState.postValue(NetworkState(Status.SUCCESS))

                    } else {
                        initialLoading.postValue(NetworkState(Status.FAILED))
                        networkState.postValue(NetworkState(Status.FAILED))
                    }
                }

                override fun onFailure(call: Call<SearchResultResponse>, t: Throwable) {
                    networkState.postValue(NetworkState(Status.FAILED))
                }
            })


    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ResultsItem>) {
        networkState.postValue(NetworkState(Status.RUNNING, "cargando"))
        RetrofitClient.retrofit.fetchFeed(params.key, API_KEY).enqueue(object : Callback<SearchResultResponse> {
            override fun onResponse(call: Call<SearchResultResponse>, response: Response<SearchResultResponse>) {
                if (response.isSuccessful) {
                    val nextKey = if (params.key == response.body()!!.totalResults) null else params.key + 1
                    Log.i("aaa",nextKey.toString())
                    callback.onResult(response.body()!!.results!!, nextKey)
                    networkState.postValue(NetworkState(Status.SUCCESS))

                } else {
                    networkState.postValue(NetworkState(Status.FAILED))
                }
            }

            override fun onFailure(call: Call<SearchResultResponse>, t: Throwable) {
                networkState.postValue(NetworkState(Status.FAILED, t.message!!))
            }
        })

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ResultsItem>) {

    }

}