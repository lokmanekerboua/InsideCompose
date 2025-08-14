package tech.lokmvne.androidbasics.navigation

import android.app.Activity
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import tech.lokmvne.androidbasics.requestPermissions.RequestPermissionsMainScreen
import tech.lokmvne.androidbasics.screens.AndroidBasicsMainScreen
import tech.lokmvne.androidbasics.sendNotifications.SendNotificationScreen
import tech.lokmvne.common.AndroidBasicsMainScreen
import tech.lokmvne.common.AndroidBasicsNavGraph
import tech.lokmvne.common.RequestPermissionsScreen
import tech.lokmvne.common.SendNotificationsScreen

fun NavGraphBuilder.androidBasicsNavGraph(navController: NavHostController, activity: Activity) {
    navigation<AndroidBasicsNavGraph>(
        startDestination = AndroidBasicsMainScreen
    ) {
        composable<AndroidBasicsMainScreen> {
            AndroidBasicsMainScreen(navController)
        }

        composable<RequestPermissionsScreen> {
            RequestPermissionsMainScreen(activity)
        }

        composable<SendNotificationsScreen> {
            SendNotificationScreen()
        }
    }
}