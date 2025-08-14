package tech.lokmvne.androidbasics.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import tech.lokmvne.androidbasics.R
import tech.lokmvne.common.AndroidBasicsScreens
import tech.lokmvne.common.SendNotificationsScreen
import tech.lokmvne.common.RequestPermissionsScreen

@Composable
fun AndroidBasicsMainScreen(navController: NavHostController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(androidBasicScreenItems) { item ->
            AndroidBasicCard(
                picture = item.picture,
                title = item.title,
                description = item.description,
                onClick = { navController.navigate(item.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(80.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
        }
    }
}


data class AndroidBasicScreenItem(
    val key: Int,
    val picture: Int,
    val title: String,
    val description: String,
    val route: AndroidBasicsScreens
)

val androidBasicScreenItems = listOf(
    AndroidBasicScreenItem(
        key = 1,
        picture = R.drawable.logosandroidicon,
        title = "Permissions",
        description = "Requesting and managing permissions.",
        route = RequestPermissionsScreen
    ),
    AndroidBasicScreenItem(
        key = 2,
        picture = R.drawable.logosandroidicon,
        title = "Notifications",
        description = "Notifications component for displaying notifications.",
        route = SendNotificationsScreen
    )
)


@Composable
fun AndroidBasicCard(
    picture: Int,
    title: String,
    description: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        onClick = onClick,
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(picture),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(38.dp),
                    contentScale = ContentScale.Fit
                )
            }
            Column(
                modifier = Modifier
                    //.fillMaxWidth()
                    .weight(5f)
                    .padding(10.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = title,
//                color = MaterialTheme.colorScheme.onSecondary,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = description,
//                color = MaterialTheme.colorScheme.onSecondary,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}