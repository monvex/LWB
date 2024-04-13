package com.example.lwb.navigation

import android.app.Activity.RESULT_OK
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lwb.auth.presentation.signin.GoogleAuthUiClient
import com.example.lwb.auth.presentation.signin.SignInScreen
import com.example.lwb.auth.presentation.signin.SignInViewModel
import com.example.lwb.LWBAppState
import com.example.lwb.statistics.presentation.main.MainScreen
import com.example.lwb.settings.presentation.SettingsScreen
import com.example.lwb.knowledgeBase.presentation.KnowledgeBaseScreen
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch

@Composable
fun NavGraph(
    appState: LWBAppState,
    context: Context,
    scope: LifecycleCoroutineScope
) {
    val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = context,
            oneTapClient = Identity.getSignInClient(context)
        )
    }
    NavHost(navController = appState.navController, startDestination = "log_in") {
        composable(BottomItem.MainPage.route) {

            MainScreen()
        }
        composable(BottomItem.KnowledgeBase.route) {
            KnowledgeBaseScreen()
        }
        composable(BottomItem.Settings.route) {
            SettingsScreen()
        }
        composable("log_in") {
            val viewModel = viewModel<SignInViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = {result ->
                    if(result.resultCode == RESULT_OK) {
                        scope.launch {
                            val signInResult = googleAuthUiClient.signInWithIntent(
                                intent = result.data ?: return@launch
                            )
                            viewModel.onSignInResult(signInResult)
                        }
                    }
                }
            )

            LaunchedEffect(key1 = state.isSignInSuccessful) {
                if(state.isSignInSuccessful) {
                    Toast.makeText(
                        context,
                        "aboba",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            SignInScreen(state = state, onSignInClick = {
                scope.launch {
                    val signInIntentSender = googleAuthUiClient.signIn()
                    launcher.launch(
                        IntentSenderRequest.Builder(
                            signInIntentSender ?: return@launch
                        ).build()
                    )
                }
            })
        }
    }
}