package com.example.examen.data.local.model

import com.example.examen.domain.model.Country

data class userCache(
    val countryList: List<Country>,
    val lastUpdate: Long,
    val offset: Int,
    val totalCount: Int
)