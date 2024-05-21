package com.example.lwb.onboarding.presentation.screens

import android.util.Log
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
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.SelectableChipColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.example.lwb.R
import com.example.lwb.onboarding.presentation.OnboardingViewModel
import com.example.lwb.settings.presentation.SettingsViewModel

@Composable
fun OnBoardingGenderScreen(
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
                .padding(bottom = 50.dp),
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
            FilterChipsWithTitleAbove(
                title = "Выберите свой пол" ,
                onClickMale = {
                    viewModel.onGenderChange("М")
                } ,
                onClickFemale = {
                    viewModel.onGenderChange("Ж")
                },
                viewModel
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilterChipsWithTitleAbove(title: String, onClickMale: () -> Unit, onClickFemale: () -> Unit, viewModel: OnboardingViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth() ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            color = Color.White,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            lineHeight = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 25.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column() {
                androidx.compose.material.Button(
                    onClick = { viewModel.onGenderChange("М") },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    enabled = viewModel.userData.value.gender == "Ж"
                ) {
                    androidx.compose.material.Text(text = "М")
                }
            }
            Column() {
                androidx.compose.material.Button(
                    onClick = { viewModel.onGenderChange("Ж") },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    enabled = viewModel.userData.value.gender == "М"
                ) {
                    androidx.compose.material.Text(text = "Ж")
                }
            }
        }
    }
}