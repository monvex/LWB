package com.example.lwb.onboarding.presentation

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lwb.R
import com.example.lwb.auth.presentation.signin.SignInState

@Composable
fun OnBoardingScreen(
    state: SignInState ,
    onSignInClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(10.dp, 100.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row() {
                Image(
                    painter = painterResource(id = R.drawable.logo) ,
                    contentDescription = "Logo",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                )
            }
            Row(
                modifier = Modifier.padding(5.dp, 100.dp, 5.dp, 5.dp)
            ){
                Text(
                    text = "Чтобы продолжить, войдите\n с аккаунтом Google",
                    color = Color.White,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 16.sp
                )
            }
            Row(){
                Button(
                    onClick = onSignInClick,
                    colors = ButtonColors(Color.White, Color.Black, Color.White, Color.White)
                ) {
                    Text(text = "Войти с Google")
                }
            }
        }
    }
}

@Preview
@Composable
fun OnBoardingScreenPreview() {

}