package tech.lokmvne.insidecompose.navigation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import tech.lokmvne.common.AndroidBasicsNavGraph
import tech.lokmvne.common.AppMainScreens
import tech.lokmvne.common.ComponentsNavGraph

@Composable
fun MainScreen(
    navController: NavHostController
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(5.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(mainScreenItems) { item ->
            AppCard(
                picture = item.picture,
                title = item.title,
                description = item.description,
                onClick = { navigationToScreen(navController, item.route) },
            )
        }
    }
}

fun navigationToScreen(navController: NavHostController, destination: AppMainScreens) {
    navController.navigate(destination) {
        launchSingleTop = true
        restoreState = true
        popUpTo(destination) {
            inclusive = true
        }
    }
}

data class MainScreenItem(
    val key: Int,
    val picture: String,
    val title: String,
    val description: String,
    val route: AppMainScreens
)

val mainScreenItems = listOf(
    MainScreenItem(
        key = 1,
        picture = "https://res.cloudinary.com/dmujoqmoq/image/upload/v1754573724/insideCompose/jetpackcompose_rlyejd.png",
        title = "Inside Compose",
        description = "A collection of composables and tools for Jetpack Compose.",
        route = ComponentsNavGraph
    ),
    MainScreenItem(
        key = 1,
        picture = "https://res.cloudinary.com/dmujoqmoq/image/upload/v1754572343/insideCompose/Android-Logo-History-1_wchgsa.png",
        title = "Android Basics",
        description = "Learn the fundamentals of Android development.",
        route = AndroidBasicsNavGraph
    ),
)