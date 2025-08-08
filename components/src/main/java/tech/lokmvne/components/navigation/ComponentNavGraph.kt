package tech.lokmvne.components.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import tech.lokmvne.common.ButtonScreen
import tech.lokmvne.common.ComponentsNavGraph
import tech.lokmvne.common.ComponentsScreen
import tech.lokmvne.common.TextScreen
import tech.lokmvne.components.screens.ButtonScreen
import tech.lokmvne.components.screens.ComponentMainScreen
import tech.lokmvne.components.screens.TextScreen

fun NavGraphBuilder.componentNavGraph(navController: NavHostController) {
    navigation<ComponentsNavGraph>(
        startDestination = ComponentsScreen
    ) {
        composable<ComponentsScreen> {
            ComponentMainScreen(navController)
        }
        composable<TextScreen> {
            TextScreen(navController)
        }

        composable<ButtonScreen> {
            ButtonScreen(navController)
        }
    }
}