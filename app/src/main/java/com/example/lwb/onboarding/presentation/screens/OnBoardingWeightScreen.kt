package com.example.lwb.onboarding.presentation.screens

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
import com.example.lwb.R
import com.example.lwb.onboarding.presentation.OnboardingViewModel
import com.example.lwb.onboarding.presentation.components.TextFieldWithTitleAbove


@Composable
fun OnBoardingWeightScreen(
    viewModel: OnboardingViewModel = hiltViewModel(),
    onClickSubmit: () -> Unit
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
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row() {
                Text(
                    text = "Введите свой вес:",
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
                    value = if (viewModel.userData.value.weight == 0) "" else viewModel.userData.value.weight.toString(),
                    onValueChange = {
                        if (it == "") {
                            viewModel.onWeightChange(0)
                        } else if ((it.length <= 3) and it.isDigitsOnly()) {
                            viewModel.onWeightChange(it.toInt())
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
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row() {
                Text(
                    text = "Введите желаемый вес:",
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
                    value = if (viewModel.userData.value.desiredWeight == 0) "" else viewModel.userData.value.desiredWeight.toString(),
                    onValueChange = {
                        if (it == "") {
                            viewModel.onDesiredWeightChange(0)
                        } else if ((it.length <= 3) and it.isDigitsOnly()) {
                            viewModel.onDesiredWeightChange(it.toInt())
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
                onClick = { if ((viewModel.userData.value.weight != 0) and (viewModel.userData.value.desiredWeight != 0)) {
                    viewModel.saveUser()
                    onClickSubmit()
                }
                          },
                colors = ButtonColors(Color.White , Color.Black , Color.White , Color.White),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text(text = "Yeah, buddy!" , fontSize = 20.sp)
            }
        }
    }
}

