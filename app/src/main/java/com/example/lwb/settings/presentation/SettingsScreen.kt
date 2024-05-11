package com.example.lwb.settings.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import com.example.lwb.core.presentation.googleAuth.UserData
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SettingsScreen(
    onSignOut: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel(),
    onNavigateToOnboarding: () -> Unit
){
    val user by viewModel.user
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                Text(
                    text = "Личные данные",
                    fontSize = 28.sp
                    )
            }
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .height(58.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Рост:",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        )
                }
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    OutlinedTextField(
                        value = if (user?.height == 0) "" else user?.height.toString(),
                        onValueChange = {
                            if (it == "") {
                                viewModel.onHeightChange(0)
                            }
                            else if ((it.length <= 3) and it.isDigitsOnly()) {
                                viewModel.onHeightChange(it.toInt())
                            }
                        },
                        textStyle = TextStyle(fontSize = 24.sp),
                        modifier = Modifier
                            .width(73.dp)
                            .height(58.dp),
                        singleLine = true,
                        shape = RoundedCornerShape(25.dp)
                        )
                }
            }
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .height(58.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Возраст:",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    OutlinedTextField(
                        value = if (user?.age == 0) "" else user?.age.toString(),
                        onValueChange = {
                            if (it == "") {
                                viewModel.onAgeChange(0)
                            }
                            else if ((it.length <= 3) and it.isDigitsOnly()) {
                                viewModel.onAgeChange(it.toInt())
                            }
                        },
                        textStyle = TextStyle(fontSize = 24.sp),
                        modifier = Modifier
                            .width(73.dp)
                            .height(58.dp),
                        singleLine = true,
                        shape = RoundedCornerShape(25.dp)
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .height(58.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Пол:",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    Row() {
                        Column() {
                            Button(
                                onClick = { viewModel.onGenderChange("М") },
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                                enabled = user?.gender == "Ж"
                            ) {
                                Text(text = "М")
                            }
                        }
                        Column() {
                            Button(
                                onClick = { viewModel.onGenderChange("Ж") },
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                                enabled = user?.gender == "М"
                            ) {
                                Text(text = "Ж")
                            }
                        }
                    }
                }
            }
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .height(58.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Вес:",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    OutlinedTextField(
                        value = if (user?.weight == 0) "" else user?.weight.toString(),
                        onValueChange = {
                            if (it == "") {
                                viewModel.onWeightChange(0)
                            }
                            else if ((it.length <= 3) and it.isDigitsOnly()) {
                                viewModel.onWeightChange(it.toInt())
                            }
                        },
                        textStyle = TextStyle(fontSize = 24.sp),
                        modifier = Modifier
                            .width(73.dp)
                            .height(58.dp),
                        singleLine = true,
                        shape = RoundedCornerShape(25.dp)
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .height(58.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Целевой вес:",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    OutlinedTextField(
                        value = if (user?.desiredWeight == 0) "" else user?.desiredWeight.toString(),
                        onValueChange = {
                            if (it == "") {
                                viewModel.onDesiredWeightChange(0)
                            }
                            else if ((it.length <= 3) and it.isDigitsOnly()) {
                                viewModel.onDesiredWeightChange(it.toInt())
                            }
                        },
                        textStyle = TextStyle(fontSize = 24.sp),
                        modifier = Modifier
                            .width(73.dp)
                            .height(58.dp),
                        singleLine = true,
                        shape = RoundedCornerShape(25.dp)
                    )
                }

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp , 30.dp , 0.dp , 0.dp),
                horizontalArrangement = Arrangement.Center
            ){
                Button(
                    onClick = onSignOut,
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black)
                ) {
                    Text(
                        text = "Выход",
                        fontSize = 24.sp,
                        color = Color.White
                    )
                }
            }
        }
        Button(onClick = onNavigateToOnboarding) {
            Text(text = "OnBoarding")
        }
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(onSignOut = {}, onNavigateToOnboarding = {})
}