package tech.lokmvne.insidecompose.navigation

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import tech.lokmvne.androidbasics.navigation.androidBasicsNavGraph
import tech.lokmvne.common.MainScreen
import tech.lokmvne.components.navigation.componentNavGraph
import tech.lokmvne.insidecompose.navigation.screens.MainScreen

@SuppressLint("ContextCastToActivity")
@Composable
fun MainNavGraph(navController: NavHostController) {
    val activity = LocalContext.current as Activity
    NavHost(
        navController = navController,
        startDestination = MainScreen,
    ) {
        composable<MainScreen> {
            MainScreen(navController)
        }
        androidBasicsNavGraph(navController, activity)
        componentNavGraph(navController)
    }
}

