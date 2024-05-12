package com.example.lwb.exerciseBase.presentation.exercisePage

import com.example.lwb.exerciseBase.components.ExerciseCard
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lwb.LWBAppState
import com.example.lwb.core.data.entities.Exercise
import com.example.lwb.core.presentation.components.BackButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExercisePageScreen(
    appState: LWBAppState,
    viewModel: ExercisePageViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val searchResults by viewModel.searchResults.collectAsState()

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
            BackButton(onClick = { appState.navController.popBackStack() })
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            item {
                OutlinedTextField(
                    value = state.searchQuery,
                    onValueChange = { viewModel.onEvent(ExercisePageEvent.OnSearchQueryChange(it)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = RoundedCornerShape(15.dp),
                    placeholder = { Text("Найдите упражнение") },
                    leadingIcon = {
                        Icon(Icons.Filled.Search, "Search Icon")
                    }
                )
            }

            items(
                if (state.searchQuery.isNotEmpty()) searchResults else state.muscleGroups.map { it }
            ) { item ->
                if (item is Exercise) {
                    ExerciseCard(exercise = item, navController = appState.navController)
                } else if (item is String) {
                    ExerciseMuscleGroupCard(
                        muscleGroupName = item,
                        onClick = { appState.navigate("exerciseList/$item") }
                    )
                }
            }
        }
    }
}

@Composable
fun ExerciseMuscleGroupCard(
    muscleGroupName: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(15.dp))
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF0F0F0)
        )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = muscleGroupName,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}