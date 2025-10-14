package com.example.examen.presentation.screens.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examen.data.local.preferences.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val preferences: UserPreferences
) : ViewModel() {

    // Estado para mostrar el diálogo
    val showDialog = mutableStateOf(false)

    // Texto del diálogo centralizado en el ViewModel
    val dialogText = """
        Arquitectura elegida: MVVM + Clean
        Interfaz de dominio usada: CountriesRepository
        Estrategia de guardado de preferencias: DataStore para persistir settings
        Estrategia de búsqueda: Filtro en HomeScreen usando botón de búsqueda
    """.trimIndent()

    // Último país visitado
    private val _lastCountryName = MutableStateFlow<String?>(null)
    val lastCountryName: StateFlow<String?> = _lastCountryName.asStateFlow()

    init {
        loadLastVisitedCountry()
    }

    private fun loadLastVisitedCountry() {
        viewModelScope.launch {
            preferences.getSelectedCountry()?.let { country ->
                _lastCountryName.value = country.name
            }
        }
    }

    fun onDialogButtonClick() {
        showDialog.value = true
    }

    fun onDialogDismiss() {
        showDialog.value = false
    }
}
