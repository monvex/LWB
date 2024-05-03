package com.example.lwb.foodDiary.presentation.diary

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DiaryScreen(
    viewModel: DiaryViewModel = hiltViewModel()
){
    val today by viewModel.todayData
    var visible by remember { mutableStateOf(false) }
    Box() {
        Box() {
            Column() {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(0.5f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "${today.consumedWater} / ${today.recommenderWater}\nмл",
                            modifier = Modifier
                                .absoluteOffset(0.dp, 105.dp)
                                .clickable( onClick = { visible = !visible } ),
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center
                        )
                        CircularProgressIndicator(
                            progress = today.consumedWater.toFloat() / today.recommenderWater,
                            modifier = Modifier
                                .rotate(180.0f)
                                .size(150.dp),
                            backgroundColor = Color.Black,
                            strokeWidth = 10.dp
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "${today.consumedCalories} / ${today.recommendedCalories}\nКкал",
                            modifier = Modifier.absoluteOffset(0.dp, 105.dp),
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center
                        )
                        CircularProgressIndicator(
                            progress = today.consumedCalories.toFloat() / today.recommendedCalories,
                            modifier = Modifier
                                .rotate(180.0f)
                                .size(150.dp),
                            color = Color.Yellow,
                            backgroundColor = Color.Black,
                            strokeWidth = 10.dp
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Divider(
                        modifier = Modifier.fillMaxWidth(0.9f),
                        color = Color.LightGray, thickness = 1.dp
                    )
                }
            }
            if(visible) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Box(modifier = Modifier.border(1.dp, Color.Black)) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(10.dp)
                        ) {
                            Row() {
                                Text("Введите количество потребленной воды")
                            }
                            Row() {
                                OutlinedTextField(
                                    value = if (today.consumedWater == 0) "" else today.consumedWater.toString(),
                                    onValueChange = {
                                        if (it == "") {
                                            viewModel.onConsumedWaterChange(0)
                                        } else if (it.isDigitsOnly()) {
                                            viewModel.onConsumedWaterChange(it.toInt())
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}