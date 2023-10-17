package com.example.angelaheely.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.angelaheely.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
            .setLevel(HttpLoggingInterceptor.Level.HEADERS)
            .setLevel(HttpLoggingInterceptor.Level.BODY)


    @Singleton
    @Provides
    fun provideHttpClient(
        @ApplicationContext context: Context, interceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val chuckerInterceptor = ChuckerInterceptor.Builder(context).build()

        val okHttpClient = OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(chuckerInterceptor)


        if (BuildConfig.DEBUG) {
            okHttpClient.addInterceptor(interceptor)
        }

        return okHttpClient.build()
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()


//    @Singleton
//    @Provides
//    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)


}