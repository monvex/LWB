package com.example.lwb

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lwb.core.data.dao.DayDao
import com.example.lwb.core.data.dao.UserDao
import com.example.lwb.core.presentation.snackbar.SnackbarManager
import com.example.lwb.navigation.BottomItem
import com.example.lwb.navigation.BottomNavigation
import com.example.lwb.navigation.NavGraph
import com.example.lwb.ui.theme.LWBTheme
import kotlinx.coroutines.CoroutineScope

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@Composable
@ExperimentalMaterialApi
fun LWBApp(
    context: Context,
    scope: LifecycleCoroutineScope,
    userDao: UserDao
){
    LWBTheme {
        val listItems = listOf(
            BottomItem.KnowledgeBase,
            BottomItem.MainPage,
            BottomItem.Settings
        )
        val appState = rememberAppState()
        val showBottomBar = appState.navController.currentBackStackEntryAsState().value?.destination?.route in listItems.map { it.route }
        val showTopBar = appState.navController.currentBackStackEntryAsState().value?.destination?.route in listItems.map { it.route }

        Surface(color = MaterialTheme.colorScheme.background) {

            Scaffold(
                bottomBar = {
                    if(showBottomBar) {
                        BottomNavigation(navController = appState.navController)
                    }
                },
                topBar = {
                    if(showTopBar) {
                        TopAppBar(
                            backgroundColor = Color.Black,
                            contentColor = Color.White,
                            modifier = Modifier
                                .height(30.dp)
                                .clip(shape = RoundedCornerShape(0.dp, 0.dp, 15.dp, 15.dp)),
                            title = {
                                Row(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    val route = appState.navController.currentBackStackEntryAsState().value?.destination?.route
                                    if (route != null) {
                                        val text = listItems.single { it.route == route }
                                        Text(text = text.title, modifier = Modifier.align(alignment = Alignment.CenterVertically))
                                    }
                                }
                            }
                        )
                    }
                },
                scaffoldState = appState.scaffoldState
            ) {
                NavGraph(appState, context, scope, userDao)
            }
        }

    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun previewTopAppBar() {
    TopAppBar(
        backgroundColor = Color.Black,
        contentColor = Color.White,
        title = {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text("test", modifier = Modifier.align(alignment = Alignment.CenterVertically))
            }
                },
        modifier = Modifier
            .clip(shape = RoundedCornerShape(0.dp, 0.dp, 15.dp, 15.dp))
    )

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