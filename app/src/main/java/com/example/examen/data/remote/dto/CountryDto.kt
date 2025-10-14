package com.example.examen.data.remote.dto

// Si usas GsonConverterFactory, estas clases no necesitan anotaciones.
// Asegúrate de que los nombres coincidan con las llaves del JSON.

data class CountryDto(
    val name: NameDto? = null,
    val capital: List<String>? = null,
    val region: String? = null,
    val languages: Map<String, String>? = null,
    val population: Long? = null,
    val flags: FlagsDto? = null,
    val cca2: String? = null
)

data class NameDto(
    val common: String? = null,
    val official: String? = null
)

data class FlagsDto(
    val png: String? = null,
    val svg: String? = null,
    val alt: String? = null
)