package com.example.angelaheely.data.repo

import com.example.angelaheely.data.source.local.LocalDataSource
import com.example.angelaheely.data.source.remote.RemoteDataSource
import com.example.angelaheely.domain.Repo
import com.example.angelaheely.domain.model.User
import javax.inject.Inject


class RepoImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : Repo {

    override fun setUser(user: User) = localDataSource.setUser(user)

    override suspend fun getCurrentUser() = localDataSource.getCurrentUser()

    override fun deleteCurrentUser() = localDataSource.deleteAll()

    override suspend fun getMedicines() = remoteDataSource.getMedicines()

}