package com.example.examen.domain.repository

import com.example.examen.domain.model.Country

interface CountryRepository {
    suspend fun getCountries(): List<Country>
    suspend fun getCountryByCode(code: String): Country?
    suspend fun getCountryByName(name: String): List<Country>
}