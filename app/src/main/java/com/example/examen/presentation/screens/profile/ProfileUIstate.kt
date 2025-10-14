package com.example.examen.presentation.screens.profile

import com.example.examen.domain.model.Country

data class ProfileUIstate(
    val countryList: List<Country> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
)
