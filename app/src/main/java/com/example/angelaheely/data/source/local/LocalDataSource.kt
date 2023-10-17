package com.example.angelaheely.data.source.local

import com.example.angelaheely.domain.model.User
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    fun setUser(user: User)

    fun getCurrentUser(): Flow<User?>

    fun deleteAll()
}