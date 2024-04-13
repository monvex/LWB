package com.example.lwb

import com.example.lwb.navigation.BottomNavigation
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lwb.core.presentation.snackbar.SnackbarManager
import com.example.lwb.navigation.BottomItem
import com.example.lwb.navigation.NavGraph
import com.example.lwb.ui.theme.LWBTheme
import kotlinx.coroutines.CoroutineScope

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@Composable
@ExperimentalMaterialApi
fun LWBApp(
    context: Context,
    scope: LifecycleCoroutineScope
){
    LWBTheme {
        val listItems = listOf(
            BottomItem.KnowledgeBase,
            BottomItem.MainPage,
            BottomItem.Settings
        )
        val appState = rememberAppState()
        val showBottomBar = appState.navController.currentBackStackEntryAsState().value?.destination?.route in listItems.map { it.route }

        Surface(color = MaterialTheme.colorScheme.background) {

            Scaffold(
                bottomBar = {
                    if(showBottomBar) {
                        BottomNavigation(navController = appState.navController)
                    }
                },
                scaffoldState = appState.scaffoldState
            ) {
                NavGraph(appState, context, scope)
            }
        }

    }

}


@Composable
fun rememberAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
    snackbarManager: SnackbarManager = SnackbarManager,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) =
    remember(scaffoldState, navController, snackbarManager, resources, coroutineScope) {
        LWBAppState(scaffoldState, navController, snackbarManager, resources, coroutineScope)
    }

@Composable
@ReadOnlyComposable
fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}