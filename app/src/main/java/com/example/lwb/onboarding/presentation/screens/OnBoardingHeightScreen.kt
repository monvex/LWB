package com.example.lwb.onboarding.presentation.screens

import android.app.StatusBarManager
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row() {
                Text(
                    text = "Введите свой рост:",
                    color = Color.White,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(bottom = 25.dp)
                )
            }
            Row() {
                OutlinedTextField(
                    value = if (viewModel.userData.value.height == 0) "" else viewModel.userData.value.height.toString(),
                    onValueChange = {
                        if (it == "") {
                            viewModel.onHeightChange(0)
                        } else if ((it.length <= 3) and it.isDigitsOnly()) {
                            viewModel.onHeightChange(it.toInt())
                        }
                    },
                    textStyle = TextStyle(fontSize = 30.sp, color = Color.White),
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth(0.3f)
                        .border(BorderStroke(1.dp, Color.White), RoundedCornerShape(10.dp)),
                    singleLine = true,
                    shape = RoundedCornerShape(5.dp)
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f),
            verticalAlignment = Alignment.CenterVertically ,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { if (viewModel.userData.value.height != 0) onClickNext() },
                colors = ButtonColors(Color.White , Color.Black , Color.White , Color.White),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text(text = "Далее" , fontSize = 20.sp)
            }
        }
    }
}
