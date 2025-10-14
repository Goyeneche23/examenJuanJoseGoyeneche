package com.example.examen.presentation.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.examen.presentation.screens.home.HomeScreen
import com.example.examen.presentation.screens.profile.ProfileScreen
import com.example.examen.presentation.screens.search.SearchScreen

sealed class BottomNavScreen(val route: String, val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    object Home : BottomNavScreen("home", "Home", Icons.Default.Home)
    object Search : BottomNavScreen("search", "Search", Icons.Default.Search)
    object Profile : BottomNavScreen("profile", "Profile", Icons.Default.Person)
}

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController(), modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = BottomNavScreen.Home.route, modifier = modifier) {
        composable(BottomNavScreen.Home.route) {
            HomeScreen()
        }
        composable(BottomNavScreen.Search.route) {
            SearchScreen() // <- ya no pasa argumentos
        }
        composable(BottomNavScreen.Profile.route) {
            ProfileScreen()
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val items = listOf(BottomNavScreen.Home, BottomNavScreen.Search, BottomNavScreen.Profile)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        items.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(screen.icon, contentDescription = screen.label) },
                label = { Text(screen.label) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
