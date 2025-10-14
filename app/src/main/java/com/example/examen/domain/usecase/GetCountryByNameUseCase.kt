package com.example.examen.domain.usecase

import com.example.examen.domain.model.Country
import com.example.examen.domain.repository.CountryRepository
import javax.inject.Inject

class GetCountryByNameUseCase @Inject constructor(
    private val repository: CountryRepository
) {
    suspend operator fun invoke(name: String): List<Country> {
        return repository.getCountryByName(name)
    }
}
