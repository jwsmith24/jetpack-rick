package com.example.jetpackrick.di

import com.example.jetpackrick.data.network.JetpackRickApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module // modules contain factory functions for Hilt
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideJetpackRickApi(retrofit: Retrofit): JetpackRickApi =
        retrofit.create(JetpackRickApi::class.java)
}