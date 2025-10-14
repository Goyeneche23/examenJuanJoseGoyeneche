package com.example.examen.data.remote.api

import com.example.examen.data.remote.dto.CountryDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Base URL esperada: https://restcountries.com/v3.1/
 * Nota: Usa @GET("all") (sin slash inicial) para que Retrofit respete la base con path v3.1/
 */
interface CountryApi {

    // Lista de países: especifica los campos para rendimiento y por regla de la API (hasta 10)
    @GET("all")
    suspend fun getAllCountries(
        @Query("fields") fields: String = "name,capital,region,languages,population,flags,cca2"
    ): List<CountryDto>

    // Detalle por código (recomendado, evita ambigüedad de name). Devuelve lista, tomaremos el primero.
    @GET("alpha/{code}")
    suspend fun getCountryByCode(
        @Path("code") code: String,
        @Query("fields") fields: String = "name,capital,region,languages,population,flags,cca2"
    ): List<CountryDto>

    // Alternativa por nombre (puede devolver múltiples). Úsalo solo si no tienes el code.
    @GET("name/{name}")
    suspend fun getCountryByName(
        @Path("name") name: String,
        @Query("fullText") fullText: Boolean = false,
        @Query("fields") fields: String = "name,capital,region,languages,population,flags,cca2"
    ): List<CountryDto>
}