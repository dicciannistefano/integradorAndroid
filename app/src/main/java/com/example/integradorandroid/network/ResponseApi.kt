package com.example.integradorandroid.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ResponseApi {

    @GET
    fun getRandomActivity(@Url url: String): Response<BoredResponse>

    @GET
    fun getActivityByType(@Url url: String): Response<BoredResponse>
}