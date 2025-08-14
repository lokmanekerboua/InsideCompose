package tech.lokmvne.insidecompose

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class InsideComposeApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}