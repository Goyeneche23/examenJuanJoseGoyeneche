package com.example.examen.data.mapper

import com.example.examen.data.remote.dto.CountryDto
import com.example.examen.domain.model.Country

fun CountryDto.toDomain(): Country {
    val name = name?.common ?: name?.official ?: "Desconocido"
    val capital = capital?.firstOrNull()
    val language = languages?.values?.firstOrNull()
    val region = region ?: "Desconocida"
    val population = population ?: 0L
    val flagUrl = flags?.png ?: flags?.svg

    return Country(
        name = name,
        capital = capital,
        region = region,
        language = language,
        population = population,
        flagUrl = flagUrl
    )
}