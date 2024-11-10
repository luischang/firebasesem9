package dev.luischang.firebasesem9.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjQsImlhdCI6MTczMTI1NjM2NCwiZXhwIjoxNzMxMzQyNzY0fQ.QpZ_unpYNvKDjQ5MmFUEv3QYjY2p-7lkJ6lCmhsT5Sw"

    private val client = OkHttpClient
                            .Builder()
                            .addInterceptor(AuthInterceptor(token))
                            .build()

    private val retrofit by lazy {
        Retrofit
            .Builder()
            .baseUrl("https://d907-200-0-166-76.ngrok-free.app/node-api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val api: ProductApiService by lazy {
        retrofit.create(ProductApiService::class.java)
    }





}