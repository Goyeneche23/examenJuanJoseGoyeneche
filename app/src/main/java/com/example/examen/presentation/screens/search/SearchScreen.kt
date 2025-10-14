package com.example.examen.presentation.screens.search

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.examen.presentation.screens.search.components.SearchTab

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    onPokemonClick: (String) -> Unit // recibe callback para cuando se haga clic
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        SearchTab(
            onPokemonClick = onPokemonClick
        )
    }
}
