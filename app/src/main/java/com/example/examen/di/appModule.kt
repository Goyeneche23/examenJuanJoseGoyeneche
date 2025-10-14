package com.example.examen.di

import com.example.examen.data.remote.api.CountryApi
import com.example.examen.data.repository.CountryRepositoryImpl
import com.example.examen.domain.repository.CountryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// di/AppModule.kt
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit
            .Builder()
            .baseUrl("https://restcountries.com/v3.1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideCcountryApi(retrofit: Retrofit): CountryApi = retrofit.create(CountryApi::class.java)

    @Provides
    @Singleton
    fun provideCountryRepository(api: CountryApi): CountryRepository = CountryRepositoryImpl(api)
}
