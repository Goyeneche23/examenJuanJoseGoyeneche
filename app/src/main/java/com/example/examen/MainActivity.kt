package com.example.examen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.navigation.compose.rememberNavController
import com.example.examen.presentation.navigation.AppNavHost
import com.example.examen.presentation.navigation.BottomBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface {
                    val navController = rememberNavController()

                    // Scaffold permite mostrar bottomBar
                    Scaffold(
                        bottomBar = { BottomBar(navController = navController) }
                    ) { innerPadding ->
                        AppNavHost(
                            navController = navController,
                            modifier = androidx.compose.ui.Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}
