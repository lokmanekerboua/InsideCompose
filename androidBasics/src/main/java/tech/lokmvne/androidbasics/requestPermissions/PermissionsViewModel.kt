package tech.lokmvne.androidbasics.requestPermissions

import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.runtime.mutableStateListOf
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PermissionsViewModel @Inject constructor() : ViewModel() {
    val permissionsQueue = mutableStateListOf<String>()
    fun onDismissDialog(permission: String) {
        permissionsQueue.remove(permission)
    }

    fun onPermissionResult(
        permission: String,
        isGranted: Boolean,
        context: Context
    ) {
        if (!isGranted && !permissionsQueue.contains(permission)) {
            if (ContextCompat.checkSelfPermission(
                    context,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                permissionsQueue.add(permission)
            }
        }
    }

    fun clearQueue() {
        permissionsQueue.clear()
    }
}