package com.example.integradorandroid.utils

object Constants {

    /**
     *
     * Bored API: an error occurs when two parameters are passed
     * when passing two parameters as indicated in the documentation, it does not find activities
     *      example: consume https://www.boredapi.com/api/activity?type=recreational&participants=2
     *      result: {"error":"No activity found with the specified parameters"}
     *
     * in this case a successful result, occurs when the API is passed the hard parameter
     *      satisfactory example: consume https://www.boredapi.com/api/activity?recreational&participants=2
     *      satisfactory result: {"activity":"Go swimming with a friend","type":"social","participants":2,"price":0.1,"link":"","key":"1505028","accessibility":0.1}

     */

    const val BASE_URL = "https://www.boredapi.com/api/"
    const val END_POINT_TYPE = "activity?"
    const val END_POINT_RANDOM = "activity?participants="
    const val END_POINT_PARTICIPANTS = "&participants="


}