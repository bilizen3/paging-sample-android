package com.flores.paging_sample_android.data.remoto.api

import com.flores.paging_sample_android.utils.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val retrofit= Retrofit.Builder().
        baseUrl(BASE_URL).
        addConverterFactory(GsonConverterFactory.create()).
        build().create(RetrofitApi::class.java)
}