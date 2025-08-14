package tech.lokmvne.common

import kotlinx.serialization.Serializable

sealed interface AndroidBasicsScreens

@Serializable
object AndroidBasicsNavGraph : AppMainScreens

@Serializable
object AndroidBasicsMainScreen : AndroidBasicsScreens

@Serializable
object RequestPermissionsScreen : AndroidBasicsScreens

@Serializable
object SendNotificationsScreen : AndroidBasicsScreens