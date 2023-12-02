package com.ajayam.bookapp.data
import com.ajayam.bookapp.model.ResponseData
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

interface ApiService {
    //Apis end points

    @GET("U60JinU8")
    suspend fun getCoursesData(): Response<ResponseData>

    //return a single instance of retrofit class
    companion object {
        var retrofitService: ApiService? = null

        fun getInstance(): ApiService {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build()

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("https://pastebin.com/raw/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(ApiService::class.java)
            }
            return retrofitService!!
        }
    }
}