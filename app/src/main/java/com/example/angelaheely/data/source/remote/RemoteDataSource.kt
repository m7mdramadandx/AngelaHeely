package com.example.angelaheely.data.source.remote

import com.example.angelaheely.domain.model.Response
import com.example.angelaheely.utils.DataState

interface RemoteDataSource {

    suspend fun getMedicines(): DataState<Response>
}