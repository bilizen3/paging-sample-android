package com.flores.paging_sample_android.data.remoto.api

import com.flores.paging_sample_android.data.Model.ResultsItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {
    @GET("movie")
    fun fetchFeed(
        @Query("page") page : String,
        @Query("api_key") api_key : String
    ): Call<ResultsItem>
}