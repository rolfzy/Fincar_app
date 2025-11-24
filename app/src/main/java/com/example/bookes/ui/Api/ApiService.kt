package com.example.bookes.ui.Api

import com.example.bookes.ui.Data.VehicleData
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("vehicle")
    suspend fun getAllVehicle(): List<VehicleData>
    @GET("vehicle/{id}")
    suspend fun getVehicleById(@Path("id") id: Int): VehicleData

}

object RetrofitClient{
    private const val BASE_URL = "https://691afc5f2d8d78557570fd5c.mockapi.io/Vehicle/"
    private val json = Json { ignoreUnknownKeys = true }

    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

        retrofit.create(ApiService::class.java)

    }




}