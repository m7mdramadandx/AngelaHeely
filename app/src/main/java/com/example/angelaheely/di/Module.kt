package com.example.angelaheely.di

import com.example.angelaheely.data.repo.RepoImpl
import com.example.angelaheely.data.source.local.LocalDataSource
import com.example.angelaheely.data.source.local.LocalDataSourceImp
import com.example.angelaheely.data.source.remote.RemoteDataSource
import com.example.angelaheely.data.source.remote.RemoteDataSourceImp
import com.example.angelaheely.domain.Repo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
sealed class ModuleDI {

    @Binds
    abstract fun providesLocalDataSource(localDataSourceImp: LocalDataSourceImp): LocalDataSource

    @Binds
    abstract fun providesRemoteDataSource(remoteDataSourceImp: RemoteDataSourceImp): RemoteDataSource

    @Binds
    abstract fun providesRepo(repoImpl: RepoImpl): Repo


}