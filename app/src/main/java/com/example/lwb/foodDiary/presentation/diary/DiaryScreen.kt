package com.example.lwb.foodDiary.presentation.diary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lwb.settings.presentation.SettingsViewModel

@Composable
fun DiaryScreen(
    viewModel: DiaryViewModel = hiltViewModel()
){
    val today by viewModel.todayData
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
                        text = "${today.consumedWater} / ${today.recommenderWater}\nлитров",
                        modifier = Modifier.absoluteOffset(0.dp, 105.dp),
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center
                        )
                    CircularProgressIndicator(
                        progress = today.consumedWater / today.recommenderWater,
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
    }
}