package com.example.examen.data.repository

import com.example.examen.data.mapper.toDomain
import com.example.examen.data.remote.api.CountryApi
import com.example.examen.domain.model.Country
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val api: CountryApi
) {
    suspend fun getCountries(): List<Country> =
        api.getAllCountries()
            .map { it.toDomain() }
            .sortedBy { it.name.lowercase() }

    suspend fun getCountryByCode(code: String): Country? =
        api.getCountryByCode(code).firstOrNull()?.toDomain()

    suspend fun getCountryByName(name: String): List<Country> =
        api.getCountryByName(name).map { it.toDomain() }
}