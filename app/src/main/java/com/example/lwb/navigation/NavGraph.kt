package com.example.lwb.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lwb.ui.LWBAppState
import com.example.lwb.statistics.presentation.main.MainScreen
import com.example.lwb.settings.presentation.SettingsScreen
import com.example.lwb.knowledgeBase.presentation.KnowledgeBaseScreen
@Composable
fun NavGraph(
    appState: LWBAppState,
) {
    NavHost(navController = appState.navController, startDestination = BottomItem.MainPage.route) {
        composable(BottomItem.MainPage.route) {
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