package com.example.integradorandroid.network

data class BoredResponse(var activity: String,
                         var type: String,
                         var participants: Int,
                         var price: Double,
                         var link: String,
                         var key: Int,
                         var accessibility: Double)
