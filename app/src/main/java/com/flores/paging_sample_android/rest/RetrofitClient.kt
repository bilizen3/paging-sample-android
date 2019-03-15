package com.flores.paging_sample_android.rest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val retrofit= Retrofit.Builder().baseUrl("http://192.168.0.100:80/wSYuraMobile/api/Mobile/").
    addConverterFactory(GsonConverterFactory.create()).build();
    retrofit2Api=retrofit.create(Retrofit2Api.class);
}