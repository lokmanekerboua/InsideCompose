package tech.lokmvne.common

import kotlinx.serialization.Serializable

sealed interface ComponentScreenRoutes

@Serializable
object ComponentsNavGraph:AppMainScreens

@Serializable
object ComponentsScreen: ComponentScreenRoutes

@Serializable
object TextScreen: ComponentScreenRoutes

@Serializable
object ButtonScreen: ComponentScreenRoutes