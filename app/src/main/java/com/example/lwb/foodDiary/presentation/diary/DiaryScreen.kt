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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.toPath
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DiaryScreen(
    viewModel: DiaryViewModel = hiltViewModel()
){
    val today by viewModel.today
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
                            text = "${today.waterIntake} / ${today.recommendedWater}\nмл",
                            modifier = Modifier.absoluteOffset(0.dp, 105.dp),
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center
                        )
                        CircularProgressIndicator(
                            progress = today.waterIntake.toFloat() / today.recommendedWater,
                            modifier = Modifier
                                .rotate(180.0f)
                                .size(150.dp)
                                .clickable(onClick = { visible = !visible }),
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
                            text = "${today.caloriesIntake} / ${today.recommendedCalories}\nКкал",
                            modifier = Modifier.absoluteOffset(0.dp, 105.dp),
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center
                        )
                        CircularProgressIndicator(
                            progress = today.caloriesIntake.toFloat() / today.recommendedCalories,
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
                        modifier = Modifier.fillMaxWidth(0.95f),
                        color = Color.LightGray, thickness = 1.dp
                    )
                }
                Row( modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                    horizontalArrangement = Arrangement.Center){
                    Text(text = "Фактическое потребление",
                        fontSize = 16.sp
                    )
                }
                Row( horizontalArrangement = Arrangement.Center ) {
                    Box( modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.TopCenter ) {
                        LinearProgressIndicator(
                            modifier = Modifier
                                .height(15.dp)
                                .fillMaxWidth(0.9f),
                            progress = 1.0f, color = Color(0.08f, 0.75f, 0.26f, 1.0f),
                            backgroundColor = Color(0.0f, 0.0f, 0.0f, 0.0f),
                        )
                        LinearProgressIndicator(
                            modifier = Modifier
                                .height(15.dp)
                                .fillMaxWidth(0.9f),
                            progress = (today.proteinsIntake + today.fatsIntake).toFloat() / (today.proteinsIntake + today.fatsIntake + today.carbohydratesIntake),
                            color = Color(0.29f, 0.59f, 0.19f, 1.0f),
                            backgroundColor = Color(0.0f, 0.0f, 0.0f, 0.0f)
                        )
                        LinearProgressIndicator(
                            modifier = Modifier
                                .height(15.dp)
                                .fillMaxWidth(0.9f),
                            progress = today.proteinsIntake.toFloat() / (today.proteinsIntake + today.fatsIntake + today.carbohydratesIntake),
                            color = Color(0.29f, 0.42f, 0.31f, 1.0f),
                            backgroundColor = Color(0.0f, 0.0f, 0.0f, 0.0f)
                        )
                    }
                }
                Row( modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp, 10.dp, 5.dp, 5.dp),
                    horizontalArrangement = Arrangement.Center){
                    Text(text = "Рекомендуемое потребление",
                        fontSize = 16.sp
                    )
                }
                Row( horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically) {
                    Box( modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.TopCenter ) {
                        LinearProgressIndicator(
                            modifier = Modifier
                                .height(15.dp)
                                .fillMaxWidth(0.9f),
                            progress = 1.0f, color = Color(0.08f, 0.75f, 0.26f, 1.0f),
                            backgroundColor = Color(0.0f, 0.0f, 0.0f, 0.0f),
                        )
                        LinearProgressIndicator(
                            modifier = Modifier
                                .height(15.dp)
                                .fillMaxWidth(0.9f),
                            progress = (today.recommendedProteins + today.recommendedFats).toFloat() / (today.recommendedProteins + today.recommendedFats + today.recommendedCarbohydrates),
                            color = Color(0.29f, 0.59f, 0.19f, 1.0f),
                            backgroundColor = Color(0.0f, 0.0f, 0.0f, 0.0f)
                        )
                        LinearProgressIndicator(
                            modifier = Modifier
                                .height(15.dp)
                                .fillMaxWidth(0.9f),
                            progress = today.recommendedProteins.toFloat() / (today.recommendedProteins + today.recommendedFats + today.recommendedCarbohydrates),
                            color = Color(0.29f, 0.42f, 0.31f, 1.0f),
                            backgroundColor = Color(0.0f, 0.0f, 0.0f, 0.0f)
                        )
                    }
                }
                Row( modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.Center ){
                    Column( modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 5.dp),
                        horizontalAlignment = Alignment.CenterHorizontally){
                        Row( verticalAlignment = Alignment.CenterVertically ) {
                            Column() {
                                Box(modifier = Modifier
                                    .drawWithCache {
                                        val roundedPolygon = RoundedPolygon(
                                            numVertices = 12,
                                            radius = size.minDimension / 2,
                                            centerX = size.width / 2,
                                            centerY = size.height / 2
                                        )
                                        val roundedPolygonPath =
                                            roundedPolygon.toPath().asComposePath()
                                        onDrawBehind {
                                            drawPath(
                                                roundedPolygonPath,
                                                color = Color(0.29f, 0.42f, 0.31f, 1.0f)
                                            )
                                        }
                                    }
                                    .size(12.dp)
                                )
                            }
                            Column() {
                                Text("Белки")
                            }
                        }
                    }
                    Column( modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 5.dp),
                        horizontalAlignment = Alignment.CenterHorizontally){
                        Row( verticalAlignment = Alignment.CenterVertically ) {
                            Column() {
                                Box(modifier = Modifier
                                    .drawWithCache {
                                        val roundedPolygon = RoundedPolygon(
                                            numVertices = 12,
                                            radius = size.minDimension / 2,
                                            centerX = size.width / 2,
                                            centerY = size.height / 2
                                        )
                                        val roundedPolygonPath =
                                            roundedPolygon.toPath().asComposePath()
                                        onDrawBehind {
                                            drawPath(
                                                roundedPolygonPath,
                                                color = Color(0.29f, 0.59f, 0.19f, 1.0f)
                                            )
                                        }
                                    }
                                    .size(12.dp)
                                )
                            }
                            Column() {
                                Text("Жиры")
                            }
                        }
                    }
                    Column( modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 5.dp),
                        horizontalAlignment = Alignment.CenterHorizontally){
                        Row( verticalAlignment = Alignment.CenterVertically ) {
                            Column() {
                                Box(modifier = Modifier
                                    .drawWithCache {
                                        val roundedPolygon = RoundedPolygon(
                                            numVertices = 12,
                                            radius = size.minDimension / 2,
                                            centerX = size.width / 2,
                                            centerY = size.height / 2
                                        )
                                        val roundedPolygonPath =
                                            roundedPolygon.toPath().asComposePath()
                                        onDrawBehind {
                                            drawPath(
                                                roundedPolygonPath,
                                                color = Color(0.08f, 0.75f, 0.26f, 1.0f)
                                            )
                                        }
                                    }
                                    .size(12.dp)
                                )
                            }
                            Column() {
                                Text("Углеводы")
                            }
                        }
                    }
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
                                    value = if (today.waterIntake == 0) "" else today.waterIntake.toString(),
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