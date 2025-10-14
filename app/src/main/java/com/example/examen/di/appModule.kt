package com.example.examen.di

import com.example.examen.data.local.preferences.UserPreferences
import com.example.examen.data.remote.api.CountryApi
import com.example.examen.data.repository.CountryRepositoryImpl
import com.example.examen.domain.repository.CountryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import android.content.Context
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import com.google.gson.Gson

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
    fun provideGson(): Gson {
        return Gson()
    }
    @Provides
    @Singleton
    fun provideCountryApi(retrofit: Retrofit): CountryApi = retrofit.create(CountryApi::class.java)

    @Provides
    @Singleton
    fun provideCountryPreferences(
        @ApplicationContext context: Context,
        gson: Gson
    ): UserPreferences {
        return UserPreferences(context, gson)
    }
    @Provides
    @Singleton
    fun provideCountryRepository(api: CountryApi,     preferences: UserPreferences
    ): CountryRepository = CountryRepositoryImpl(api, preferences)
}

