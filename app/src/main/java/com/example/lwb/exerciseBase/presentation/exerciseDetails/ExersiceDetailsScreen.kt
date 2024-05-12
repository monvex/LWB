package com.example.lwb.exerciseBase.presentation.exerciseDetails

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.lwb.core.data.entities.Exercise
import com.example.lwb.core.presentation.components.BackButton
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseDetailsScreen(exerciseId: Int,
                          navController: NavController,
                          viewModel: ExerciseDetailsViewModel = hiltViewModel()) {
    val exercise = remember { mutableStateOf<Exercise?>(null) }

    LaunchedEffect(exerciseId) {
        exercise.value = viewModel.getExerciseById(exerciseId)
    }

    var currentImageIndex by remember { mutableIntStateOf(0) }
    val images = exercise.value?.let { listOfNotNull(it.imageFirst, it.imageSecond) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    titleContentColor = Color.White,
                    containerColor = Color.Black
                ),
                modifier = Modifier
                    .height(30.dp)
                    .clip(RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = 16.dp,
                        bottomEnd = 16.dp
                    )),
                title = {
                    Text(
                        text = "Упражнения",
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            )
        },
        floatingActionButton = {
            BackButton(onClick = { navController.popBackStack() })
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues -> Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Положение")
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .background(Color.LightGray)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                AnimatedContent(
                    targetState = currentImageIndex,
                    label = "ExerciseDetailsImage"
                ) { targetIndex ->
                    // Анимация перехода
                    val targetTranslation by animateFloatAsState(
                        targetValue = if (targetIndex == currentImageIndex) 0f else if (targetIndex < currentImageIndex) -2000f else 2000f,
                        label = ""
                    )

                    Image(
                        painter = rememberAsyncImagePainter(model = images?.get(targetIndex)),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .graphicsLayer {
                                translationX = targetTranslation
                            }
                    )
                }
                if (images != null) {
                    if (images.size > 1) {
                        if (currentImageIndex > 0) {
                            Button(
                                onClick = { currentImageIndex-- },
                                modifier = Modifier
                                    .align(Alignment.CenterStart)
                                    .padding(8.dp),
                                shape = RoundedCornerShape(50),
                                colors = ButtonDefaults.buttonColors(Color.Black)
                            ) {
                                Text(text = "<", color = Color.White)
                            }
                        }
                        if (currentImageIndex < images.size - 1) {
                            Button(
                                onClick = { currentImageIndex++ },
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .padding(8.dp),
                                shape = RoundedCornerShape(50),
                                colors = ButtonDefaults.buttonColors(Color.Black)
                            ) {
                                Text(text = ">", color = Color.White)
                            }
                        }
                    }
                }
            }
            Text(text = "Описание:", modifier = Modifier.padding(top = 16.dp))
            Text(text = exercise.value?.description ?: "Упражнение не найдено")
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}