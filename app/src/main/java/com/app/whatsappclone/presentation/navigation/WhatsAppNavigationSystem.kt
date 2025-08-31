package com.app.whatsappclone.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.whatsappclone.presentation.callsScreen.CallScreen
import com.app.whatsappclone.presentation.communityScreen.CommunityScreen
import com.app.whatsappclone.presentation.homeScreen.HomeScreen
import com.app.whatsappclone.presentation.splashscreen.SplashScreen
import com.app.whatsappclone.presentation.updateScreen.UpdateScreen
import com.app.whatsappclone.presentation.userRegistrationScreen.UserRegistrationScreen
import com.app.whatsappclone.presentation.welcomeScreen.WelcomScreen

@Composable
fun WhatsAppNavigationSystem()
{
    val navController = rememberNavController()

    NavHost(startDestination = Routes.SplashScreen , navController = navController)
    {
        composable<Routes.SplashScreen>{
            SplashScreen(navController)
        }

        composable<Routes.WelcomeScrenn>{
            WelcomScreen(navController)
        }

        composable<Routes.UserRegistrationScreen>{
            UserRegistrationScreen(navController)
        }

        composable<Routes.HomeScreen>{
            HomeScreen(navController)
        }

        composable<Routes.UpdateScreen>{
            UpdateScreen(navController)

        }

        composable<Routes.CommunityScreen>{
            CommunityScreen(navController)
        }

        composable<Routes.CallScreen>{
            CallScreen(navController)
        }
    }
}
