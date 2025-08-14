// Top-level build file where you can add configuration options common to all sub-projects/modules.

extra["AppJavaVersion"] = JavaVersion.VERSION_21
extra["AppMinSdkVersion"] = 28
extra["AppCompileSdkVersion"] = 36
extra["AppTargetSdkVersion"] = 36

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.google.devtools.ksp) apply false
    alias(libs.plugins.google.dagger.hilt) apply false
    alias(libs.plugins.kotlinx.serialization) apply false
    alias(libs.plugins.android.library) apply false
}