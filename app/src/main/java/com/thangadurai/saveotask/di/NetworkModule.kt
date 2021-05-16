package com.thangadurai.saveotask.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.thangadurai.saveotask.data.network.ApiHelper
import com.thangadurai.saveotask.data.network.ApiUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }


    @Provides
    @Singleton
    internal fun provideApiHelper(retrofit: Retrofit) = retrofit.create(ApiHelper::class.java)

    @Provides
    @Singleton
    internal fun provideRetrofit(gson: Gson): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(ApiUtils.BASE_URL)
            .build()

}