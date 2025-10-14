package com.example.examen.presentation.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.examen.presentation.screens.home.HomeScreen
import com.example.examen.presentation.screens.profile.ProfileScreen
import com.example.examen.presentation.screens.detail.CountryDetailScreen
import com.example.examen.presentation.screens.search.SearchScreen

sealed class BottomNavScreen(val route: String, val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    object Home : BottomNavScreen("home", "Home", Icons.Default.Home)
    object Search : BottomNavScreen("search", "Search", Icons.Default.Search)
    object Profile : BottomNavScreen("profile", "Profile", Icons.Default.Menu)
}

sealed class Screens(val route: String) {
    object Detail : Screens("detail/{country}") {
        fun createRoute(country: String) = "detail/$country"
    }
}

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController(), modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = BottomNavScreen.Home.route, modifier = modifier) {
        composable(BottomNavScreen.Home.route) {
            HomeScreen(        onCountryClick = { country ->
                navController.navigate(Screens.Detail.createRoute(country))
            })
        }
        composable(route = "search") {
            SearchScreen(
                onPokemonClick = { name ->
                    navController.navigate("detail/$name")
                }
            )
        }

        composable(BottomNavScreen.Profile.route) {
            ProfileScreen(
                onCountryClick = { country ->
                    navController.navigate(Screens.Detail.createRoute(country))
                },
            )
        }
        composable(
            route = Screens.Detail.route,
            arguments = listOf(navArgument("country") { type = NavType.StringType }),
        ) { backStackEntry ->
            val country = backStackEntry.arguments?.getString("country") ?: ""
            CountryDetailScreen(
                nameCountry = country,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {  //BotomBar
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
