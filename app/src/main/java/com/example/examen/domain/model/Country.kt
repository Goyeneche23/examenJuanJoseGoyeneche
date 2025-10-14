package com.example.examen.domain.model

data class Country(
    val name: String,
    val capital: String?,
    val region: String,
    val language: String?,
    val population: Long,
    val flagUrl: String?
)