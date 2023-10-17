package com.example.angelaheely.data.source.api

import com.example.angelaheely.domain.model.DrugsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("v3/7de340e2-eec4-463f-91a1-63bb64191be9")
    suspend fun getMedicines(): Response<DrugsResponse>

}