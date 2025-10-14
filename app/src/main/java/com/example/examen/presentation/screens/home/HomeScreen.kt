package com.example.examen.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.examen.R
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onCountryClick: (String) -> Unit // callback para navegar al DetailScreen
) {
    val showDialog by viewModel.showDialog
    val lastCountryName by viewModel.lastCountryName.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.fondovegetta),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "APPlâent")
            Spacer(modifier = Modifier.height(8.dp))

            // Botón del diálogo
            Button(onClick = { viewModel.onDialogButtonClick() }) {
                Text(text = "Información")
            }

            // Botón del último país visitado
            lastCountryName?.let { country ->
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { onCountryClick(country) }) {
                    Text("Último país visitado: $country")
                }
            }

            // Mostrar diálogo si showDialog es true
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { viewModel.onDialogDismiss() },
                    confirmButton = {
                        Button(onClick = { viewModel.onDialogDismiss() }) {
                            Text("Cerrar")
                        }
                    },
                    title = { Text("Información") },
                    text = { Text(viewModel.dialogText) }
                )
            }
        }
    }
}
