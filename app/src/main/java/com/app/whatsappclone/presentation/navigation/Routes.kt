package com.app.whatsappclone.presentation.navigation

import kotlinx.serialization.Serializable


sealed class Routes {
    @Serializable
    data object SplashScreen : Routes()

    @Serializable
    data object WelcomeScrenn : Routes()

    @Serializable
    data object UserRegistrationScreen : Routes()

    @Serializable
    data object HomeScreen : Routes()

    @Serializable
    data object UpdateScreen : Routes()

    @Serializable
    data object CommunityScreen : Routes()

    @Serializable
    data object CallScreen : Routes()
}