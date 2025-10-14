package com.example.examen.data.repository

import com.example.examen.data.mapper.toDomain
import com.example.examen.data.remote.api.CountryApi
import com.example.examen.domain.model.Country
import com.example.examen.domain.repository.CountryRepository

import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val api: CountryApi
) : CountryRepository {
    override suspend fun getCountries(): List<Country> =
        api.getAllCountries()
            .map { it.toDomain() }
            .sortedBy { it.name.lowercase() }

    override suspend fun getCountryByCode(code: String): Country? =
        api.getCountryByCode(code).firstOrNull()?.toDomain()

    override suspend fun getCountryByName(name: String): List<Country> =
        api.getCountryByName(name).map { it.toDomain() }
}