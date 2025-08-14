package tech.lokmvne.androidbasics.requestPermissions

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel

val appPermissions = arrayOf(
    Manifest.permission.CAMERA,
    Manifest.permission.WRITE_EXTERNAL_STORAGE,
    Manifest.permission.POST_NOTIFICATIONS,
    Manifest.permission.CALL_PHONE,
    Manifest.permission.ACCESS_MEDIA_LOCATION
)

@SuppressLint("ContextCastToActivity")
@Composable
fun RequestPermissionsMainScreen(activity: Activity) {
    val permissionViewModel = hiltViewModel<PermissionsViewModel>()
    val permissionsQueue = permissionViewModel.permissionsQueue
    val context = LocalContext.current
    val permissionsLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            permissions.forEach { permission, isGranted ->
                permissionViewModel.onPermissionResult(
                    permission = permission,
                    isGranted = isGranted,
                    context
                )
            }
        }
    )


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ElevatedButton(
                onClick = {
                    permissionsLauncher.launch(appPermissions)
                }
            ) {
                Text("Request Permissions", color = MaterialTheme.colorScheme.onBackground)
            }
        }

        permissionsQueue.forEach { permission ->
            val isPermanentlyDeclined = isPermanentlyDeclined(activity, permission)
            PermissionDialog(
                permissionTextProvider = getPermissionTextProvider(permission),
                isPermanentlyDeclined = isPermanentlyDeclined,
                onDismiss = { permissionViewModel.onDismissDialog(permission) },
                onOkClick = {
                    permissionViewModel.onDismissDialog(permission)
                    if (isPermanentlyDeclined) {
                        permissionsLauncher.launch(arrayOf(permission))
                    }
                },
                onGoToAppSettingsClick = {
                    permissionViewModel.onDismissDialog(permission)
                    activity.openAppSettings()
                },
            )
        }
    }

}

fun Activity.openAppSettings() {
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName, null)
    ).also(::startActivity)
}

fun isPermanentlyDeclined(activity: Activity, permission: String): Boolean {
    return !ActivityCompat.shouldShowRequestPermissionRationale(
        activity,
        permission
    ) && ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_DENIED
}

fun getPermissionTextProvider(permission: String): PermissionTextProvider {
    return when (permission) {
        Manifest.permission.CAMERA -> CameraPermissionTextProvider()
        Manifest.permission.WRITE_EXTERNAL_STORAGE -> StoragePermissionTextProvider()
        Manifest.permission.POST_NOTIFICATIONS -> NotificationPermissionTextProvider()
        Manifest.permission.CALL_PHONE -> PhoneCallPermissionTextProvider()
        Manifest.permission.ACCESS_MEDIA_LOCATION -> MediaLocationPermissionTextProvider()
        else -> DefaultPermissionTextProvider()
    }
}

@Composable
fun PermissionDialog(
    permissionTextProvider: PermissionTextProvider,
    isPermanentlyDeclined: Boolean,
    onDismiss: () -> Unit,
    onOkClick: () -> Unit,
    onGoToAppSettingsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = { onDismiss() },
        title = { Text(text = "Permission Required") },
        text = {
            Text(
                text = permissionTextProvider.getDescription(isPermanentlyDeclined)
            )
        },
        dismissButton = {
            Text(
                text = "Dismiss",
                modifier = Modifier
                    .clickable { onDismiss() }
                    .padding(horizontal = 10.dp)
            )
        },
        confirmButton = {
            if (isPermanentlyDeclined) {
                Text(
                    text = "Grant",
                    modifier = Modifier
                        .clickable { onGoToAppSettingsClick() }
                        .padding(horizontal = 10.dp)
                )
            } else {
                Text(
                    text = "OK",
                    modifier = Modifier.clickable { onOkClick() }
                )
            }
        }
    )
}

interface PermissionTextProvider {
    fun getDescription(isPermanentlyDeclined: Boolean): String
}

class CameraPermissionTextProvider : PermissionTextProvider {
    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if (isPermanentlyDeclined) {
            "It seems you permanently declined camera permission. " + "You can go to the app settings to grant it."
        } else {
            "This app needs access to your camera so that your friends " + "can see you in a call."
        }
    }
}

class MediaLocationPermissionTextProvider : PermissionTextProvider {
    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if (isPermanentlyDeclined) {
            "It seems you permanently declined media location permission. " +
                    "You can go to the app settings to grant it."
        } else {
            "This app needs access to media location information to function properly."
        }
    }
}

class StoragePermissionTextProvider : PermissionTextProvider {
    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if (isPermanentlyDeclined) {
            "It seems you permanently declined microphone permission. " + "You can go to the app settings to grant it."
        } else {
            "This app needs access to your microphone so that your friends " + "can hear you in a call."
        }
    }
}

class PhoneCallPermissionTextProvider : PermissionTextProvider {
    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if (isPermanentlyDeclined) {
            "It seems you permanently declined phone calling permission. " + "You can go to the app settings to grant it."
        } else {
            "This app needs phone calling permission so that you can talk " + "to your friends."
        }
    }
}

class NotificationPermissionTextProvider : PermissionTextProvider {
    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if (isPermanentlyDeclined) {
            "It seems you permanently declined phone calling permission. " + "You can go to the app settings to grant it."
        } else {
            "This app needs phone calling permission so that you can talk " + "to your friends."
        }
    }
}

class DefaultPermissionTextProvider : PermissionTextProvider {
    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if (isPermanentlyDeclined) {
            "It seems you permanently declined an important permission. " + "You can go to the app settings to grant it."
        } else {
            "This app needs this permission so that it can correctly " + "function."
        }
    }
}