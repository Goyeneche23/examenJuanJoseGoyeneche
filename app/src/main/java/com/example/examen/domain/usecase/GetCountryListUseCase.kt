package com.example.examen.domain.usecase

import com.example.examen.domain.common.Result
import com.example.examen.domain.model.Country
import com.example.examen.domain.repository.CountryRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCountryListUseCase
@Inject
constructor(
    private val repository: CountryRepository,
) {
    // Puede ser llamado como useCase()
    operator fun invoke(): Flow<Result<List<Country>>> =
        flow {
            try {
                // Primer valor: Loading
                emit(Result.Loading)

                // Obtiene datos
                val pokemonList = repository.getCountries()

                // Segundo valor: Success con datos
                emit(Result.Success(pokemonList))
            } catch (e: Exception) {
                // O Error si algo falla
                emit(Result.Error(e))
            }
        }
}
