package com.example.lwb.settings.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lwb.auth.presentation.signin.UserData

@Composable
fun SettingsScreen(
    userData: UserData = UserData(userId = "qwe", username = null, gender = "М", age = 20, weight = 80, height = 180, desiredWeight = 85),
    onSignOut: () -> Unit
){
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
                        value = userData.height.toString(),
                        onValueChange = {},
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
                                onClick = {},
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                                enabled = userData.gender == "Ж"
                            ) {
                                Text(text = "М")
                            }
                        }
                        Column() {
                            Button(
                                onClick = {},
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                                enabled = userData.gender == "М"
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
                        value = userData.height.toString(),
                        onValueChange = {},
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
                        value = userData.height.toString(),
                        onValueChange = {},
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
                    .padding(0.dp, 30.dp, 0.dp, 0.dp),
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
    }
}