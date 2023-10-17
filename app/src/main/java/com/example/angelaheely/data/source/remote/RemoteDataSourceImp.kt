package com.example.angelaheely.data.source.remote

import com.example.angelaheely.BuildConfig
import com.example.angelaheely.data.source.api.ApiService
import com.example.angelaheely.domain.model.DrugsResponse
import com.example.angelaheely.utils.DataState
import com.example.angelaheely.utils.handleException
import com.example.angelaheely.utils.handleResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(
    private val retrofit: Retrofit,
    private val okHttpClient: OkHttpClient
) : RemoteDataSource {

    override suspend fun getMedicines(): DataState<DrugsResponse> =
        withContext(Dispatchers.IO) {
            try {
                val response =
                    retrofit.newBuilder()
                        .baseUrl(BuildConfig.BASE_URL)
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(ApiService::class.java)
                        .getMedicines()

                response.handleResponse()
            } catch (t: Throwable) {
                t.handleException()
            }
        }

}