package com.example.lwb.onboarding.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lwb.R
import com.example.lwb.onboarding.presentation.OnboardingViewModel

@Composable
fun OnBoardingPage(onBoardingElement: OnBoardingElement, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(10.dp , 100.dp),
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
                    text = onBoardingElement.description,
                    color = Color.White,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 16.sp
                )
            }
            Row {
                OutlinedTextField(
                    value = "",
                    onValueChange = {
                        if (it == "") {
                        }
                        else if ((it.length <= 3) and it.isDigitsOnly()) {

                        }
                    },
                    textStyle = TextStyle(fontSize = 24.sp) ,
                    modifier = Modifier
                        .width(73.dp)
                        .height(58.dp)
                        .border(BorderStroke(1.dp, Color.White), RoundedCornerShape(25.dp)),
                    singleLine = true,
                    shape = RoundedCornerShape(25.dp)
                )
            }

            Row(){
                Button(
                    onClick = { onClick } ,
                    colors = ButtonColors(Color.White, Color.Black, Color.White, Color.White)
                ) {
                    Text(text = onBoardingElement.buttonText)
                }
            }
        }
        }
    }


@Preview
@Composable
fun OnBoardingPage1Preview() {
    OnBoardingPage(onBoardingElement = OnBoardingElement.First, onClick = {})
}

@Preview(apiLevel = 33)
@Composable
fun OnBoardingPage2Preview() {
    OnBoardingPage(onBoardingElement = OnBoardingElement.Second, onClick = {})
}

@Preview
@Composable
fun OnBoardingPage3Preview() {
    OnBoardingPage(onBoardingElement = OnBoardingElement.Third, onClick = {})
}

@Preview
@Composable
fun OnBoardingPage4Preview() {
    OnBoardingPage(onBoardingElement = OnBoardingElement.Fourth, onClick = {})
}

@Preview
@Composable
fun OnBoardingPage5Preview() {
    OnBoardingPage(onBoardingElement = OnBoardingElement.Fifth, onClick = {})
}

