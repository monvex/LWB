package com.example.lwb.onboarding.presentation.screens

import android.app.StatusBarManager
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.example.lwb.R
import com.example.lwb.core.presentation.snackbar.SnackbarManager
import com.example.lwb.onboarding.presentation.OnboardingViewModel
import com.example.lwb.onboarding.presentation.components.TextFieldWithTitleAbove
import kotlinx.coroutines.launch


@Composable
fun OnBoardingHeightScreen(
    viewModel: OnboardingViewModel = hiltViewModel() ,
    onClickNext: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(start = 10.dp , end = 10.dp , top = 100.dp) ,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .padding(bottom = 50.dp) ,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo) ,
                contentDescription = "Logo" ,
                contentScale = ContentScale.FillWidth ,
                modifier = Modifier
                    .fillMaxWidth(0.6f)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
        ) {
            TextFieldWithTitleAbove(
                title = "Введите свой рост" ,
                onValueChange = {
                    if (it == "") {
                        viewModel.onHeightChange(0)
                    } else {
                        viewModel.onHeightChange(it.toInt())
                    }
                },
                text = viewModel.userData.value.age.toString()
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f),
            verticalAlignment = Alignment.CenterVertically ,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = onClickNext ,
                colors = ButtonColors(Color.White , Color.Black , Color.White , Color.White),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text(text = "Далее" , fontSize = 20.sp)
            }
        }
    }
}



@Preview
@Composable
fun OnBoardingHeightScreenPreview() {
    OnBoardingHeightScreen( onClickNext = {})
}

