package com.cnrture.uitestcomposemaestro.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cnrture.uitestcomposemaestro.ui.home.HomeScreen
import com.cnrture.uitestcomposemaestro.ui.login.LoginScreen

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.Login,
    ) {
        composable<Screen.Login> {
            LoginScreen(
                onNavigateHome = { navController.navigate(Screen.Home) }
            )
        }

        composable<Screen.Home> {
            HomeScreen()
        }
    }
}