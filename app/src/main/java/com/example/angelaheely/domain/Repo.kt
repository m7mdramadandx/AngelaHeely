package com.example.angelaheely.domain

import com.example.angelaheely.domain.model.DrugsResponse
import com.example.angelaheely.domain.model.User
import com.example.angelaheely.utils.DataState
import kotlinx.coroutines.flow.Flow

interface Repo {

    fun setUser(user: User)

    suspend fun getCurrentUser(): Flow<User?>

    fun deleteCurrentUser()

    suspend fun getMedicines(): DataState<DrugsResponse>
}
