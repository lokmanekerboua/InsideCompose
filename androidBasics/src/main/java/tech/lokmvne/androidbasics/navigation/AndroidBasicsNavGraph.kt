package tech.lokmvne.androidbasics.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import tech.lokmvne.androidbasics.screens.AndroidBasicsMainScreen
import tech.lokmvne.common.AndroidBasicsMainScreen
import tech.lokmvne.common.AndroidBasicsNavGraph

fun NavGraphBuilder.androidBasicsNavGraph(navController: NavHostController) {
    navigation<AndroidBasicsNavGraph>(
        startDestination = AndroidBasicsMainScreen
    ) {
        composable<AndroidBasicsMainScreen> {
            AndroidBasicsMainScreen(navController)
        }
    }
}