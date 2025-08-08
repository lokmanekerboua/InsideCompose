package tech.lokmvne.insidecompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import tech.lokmvne.androidbasics.navigation.androidBasicsNavGraph
import tech.lokmvne.common.MainScreen
import tech.lokmvne.components.navigation.componentNavGraph
import tech.lokmvne.insidecompose.navigation.screens.MainScreen

@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = MainScreen,
    ) {
        composable<MainScreen> {
            MainScreen(navController)
        }
        androidBasicsNavGraph(navController)
        componentNavGraph(navController)
    }
}

