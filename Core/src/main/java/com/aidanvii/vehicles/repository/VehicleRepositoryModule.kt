package com.aidanvii.vehicles.repository

import com.aidanvii.vehicles.repository.datasource.VehicleApiService
import com.aidanvii.toolbox.Provider
import com.aidanvii.vehicles.common.utils.CoroutineDispatchers
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class VehicleRepositoryModule : Provider<VehicleRepository> {

    companion object {
        const val baseUrl = "https://m.mobile.de"
    }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor()
            .build()
    }

    private fun OkHttpClient.Builder.addInterceptor(): OkHttpClient.Builder =
        addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC })

    private val moshi: Moshi by lazy {
        Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit
            .Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    private val vehicleApiService: VehicleApiService by lazy {
        retrofit.create(VehicleApiService::class.java)
    }

    override fun invoke(): VehicleRepository = VehicleRepositoryImpl(
        apiService = vehicleApiService,
        dispatchers = CoroutineDispatchers.DEFAULT
    )
}