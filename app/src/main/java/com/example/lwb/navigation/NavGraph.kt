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
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.lwb.core.presentation.googleAuth.GoogleAuthUiClient
import com.example.lwb.auth.presentation.signin.SignInScreen
import com.example.lwb.auth.presentation.signin.SignInViewModel
import com.example.lwb.LWBAppState
import com.example.lwb.exerciseBase.presentation.exerciseDetails.ExerciseDetailsScreen
import com.example.lwb.exerciseBase.presentation.exerciseList.ExerciseListScreen
import com.example.lwb.exerciseBase.presentation.exercisePage.ExercisePageScreen
import com.example.lwb.foodDiary.presentation.diary.DiaryScreen
import com.example.lwb.foodDiary.presentation.foodAdding.FoodAddingScreen
import com.example.lwb.statistics.presentation.main.MainScreen
import com.example.lwb.settings.presentation.SettingsScreen
import com.example.lwb.knowledgeBase.presentation.knowledge_base_screen.KnowledgeBaseScreen
import com.example.lwb.onboarding.presentation.components.OnBoardingAgeScreen
import com.example.lwb.onboarding.presentation.components.OnBoardingHeightScreen
import com.example.lwb.onboarding.presentation.components.OnBoardingSexScreen
import com.example.lwb.onboarding.presentation.components.OnBoardingWeightScreen
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
    NavHost(navController = appState.navController, startDestination = BottomItem.MainPage.route) {
        composable(BottomItem.MainPage.route) {
            MainScreen (appState.navController)
        }
        composable(BottomItem.KnowledgeBase.route) {
            KnowledgeBaseScreen(appState.navController)
        }
        composable(BottomItem.Settings.route) {
            SettingsScreen(
                onSignOut = {
                scope.launch {
                    googleAuthUiClient.signOut()
                    Toast.makeText(
                        context,
                        "Успешный успех",
                        Toast.LENGTH_LONG
                    ).show()
                    appState.navController.popBackStack()
                }
            },
                onNavigateToOnboarding = {appState.navigate("onBoarding")})
        }
        composable("log_in") {
            val viewModel = viewModel<SignInViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()

            LaunchedEffect(key1 = Unit) {
                if(googleAuthUiClient.getSignedInUser() != null) {
                    appState.navigate(BottomItem.MainPage.route)
                }
            }

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
                        "Успех успешный",
                        Toast.LENGTH_LONG
                    ).show()
                    appState.navigate(BottomItem.MainPage.route)
                    viewModel.resetState()
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
        composable("foodDiary") {
            DiaryScreen(appState.navController)
        }
        composable("exercisePage") {
            ExercisePageScreen(appState = appState);
        }
        composable("foodAdding") {
            FoodAddingScreen(appState.navController);
        }
        composable("exerciseList/{muscleGroup}") { backStackEntry ->
            val muscleGroup = backStackEntry.arguments?.getString("muscleGroup")
            if (muscleGroup != null) {
                ExerciseListScreen(muscleGroup = muscleGroup, navController = appState.navController)
            }
        }
        composable("exerciseDetails/{exerciseId}") { backStackEntry ->
            val exerciseId = backStackEntry.arguments?.getString("exerciseId")?.toIntOrNull()
            if (exerciseId != null) {
                ExerciseDetailsScreen(exerciseId = exerciseId, navController = appState.navController)
            }
        }
        onBoardingGraph(appState)
    }

}
fun NavGraphBuilder.onBoardingGraph(appState: LWBAppState) {
    navigation("toOnBoardingSexScreen", "onBoarding") {
        composable("toOnBoardingSexScreen") {
            OnBoardingSexScreen(
                onClickNext = {
                    appState.navigate("toOnBoardingAgeScreen")
                }
            )
        }
        composable("toOnboardingAgeScreen") {
            OnBoardingAgeScreen(
                onClickNext = {
                    appState.navigate("toOnBoardingHeightScreen")
                }
            )
        }
        composable("toOnboardingHeightScreen") {
            OnBoardingHeightScreen(
                onClickNext = {
                    appState.navigate("toOnBoardingWeightScreen")
                }
            )
        }
        composable("toOnboardingWeightScreen") {
            OnBoardingWeightScreen(
                onClickSubmit = {
                    appState.clearAndNavigate(BottomItem.Settings.route)
                }
            )
        }
    }
}