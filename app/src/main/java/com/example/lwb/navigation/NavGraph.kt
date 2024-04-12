package com.example.lwb.navigation

import android.app.Application
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lwb.auth.presentation.signin.GoogleAuthUiClient
import com.example.lwb.ui.LWBAppState
import com.example.lwb.statistics.presentation.main.MainScreen
import com.example.lwb.settings.presentation.SettingsScreen
import com.example.lwb.knowledgeBase.presentation.KnowledgeBaseScreen
import com.google.android.gms.auth.api.identity.Identity

@Composable
fun NavGraph(
    appState: LWBAppState,
    context: Context
) {
    NavHost(navController = appState.navController, startDestination = BottomItem.MainPage.route) {
        composable(BottomItem.MainPage.route) {
            val googleAuthUiClient by lazy {
                GoogleAuthUiClient(
                    context = context,
                    oneTapClient = Identity.getSignInClient(context)
                )
            }
            MainScreen()
        }
        composable(BottomItem.KnowledgeBase.route) {
            KnowledgeBaseScreen()
        }
        composable(BottomItem.Settings.route) {
            SettingsScreen()
        }
    }
}