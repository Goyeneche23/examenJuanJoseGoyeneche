package com.example.examen.data.repository

import com.example.examen.data.mapper.toDomain
import com.example.examen.data.remote.api.CountryApi
import com.example.examen.domain.model.Country
import com.example.examen.domain.repository.CountryRepository
import com.example.examen.data.local.preferences.UserPreferences


import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val api: CountryApi,
    private val preferences: UserPreferences,
) : CountryRepository {

    override suspend fun getCountries(): List<Country> {
        // 1️⃣ Intentar obtener del cache
        val cached = preferences.getCountryCache()
        if (cached != null && preferences.isCacheValid()) {
            return cached.countryList
        }

        // 2️⃣ Si no hay cache válido, traer de la API
        val countries = api.getAllCountries()
            .map { it.toDomain() }
            .sortedBy { it.name.lowercase() }

        // 3️⃣ Guardar en cache
        preferences.saveCountryList(countries, offset = 0, totalCount = countries.size)

        return countries
    }

    override suspend fun getCountryByCode(code: String): Country? =
        api.getCountryByCode(code).firstOrNull()?.toDomain()

    override suspend fun getCountryByName(name: String): List<Country> {
        // Intentar obtener del cache primero
        preferences.getCountryCache()?.let { cache ->
            if (preferences.isCacheValid()) {
                val result = cache.countryList.filter { it.name.contains(name, ignoreCase = true) }
                if (result.isNotEmpty()) return result
            }
        }

        return try {
            // Si no está en cache o expiró, obtener de la API
            api.getCountryByName(name).map { it.toDomain() }
        } catch (e: Exception) {
            // Si hay error, intentar buscar en cache aunque haya expirado
            preferences.getCountryCache()?.countryList
                ?.filter { it.name.contains(name, ignoreCase = true) }
                ?: emptyList()
        }
    }
}