package com.ajayam.bookapp.data

import android.util.Log
import com.ajayam.bookapp.model.ResponseData
import com.google.gson.Gson
import com.google.gson.JsonObject
import retrofit2.Response

/**A repository class isolates the data sources from the rest of the app and provides a
 * clean API for data access to the rest of the app.*/


class DataRepository constructor(private val apiService: ApiService) {

    suspend fun getCoursesData( ): Response<ResponseData> {
        Log.e("TAG", "getCoursesData11: "+Gson().toJson(apiService.getCoursesData()) )
       return apiService.getCoursesData()
    }

}