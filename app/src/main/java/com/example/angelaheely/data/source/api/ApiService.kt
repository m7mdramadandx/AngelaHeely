package com.example.angelaheely.data.source.api

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    //    https://run.mocky.io/v3/a26eac52-7756-4c1d-b3a7-8107b0981bcc
    @GET("v3/a26eac52-7756-4c1d-b3a7-8107b0981bcc")
    suspend fun getMedicines(): Response<com.example.angelaheely.domain.model.Response>

}