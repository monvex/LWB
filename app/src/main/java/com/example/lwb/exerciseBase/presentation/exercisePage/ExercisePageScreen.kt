package com.example.lwb.exerciseBase.presentation.exercisePage

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lwb.R
import com.example.lwb.core.data.entities.Exercise

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExercisePageScreen(
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
                modifier = Modifier.height(30.dp)
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
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            OutlinedTextField(
                value = state.searchQuery,
                onValueChange = { viewModel.onEvent(ExercisePageEvent.OnSearchQueryChange(it)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = { Text("Пoиск") }
            )

            if (state.searchQuery.isNotEmpty()) {
                LazyColumn {
                    items(searchResults) { exercise ->
                        ExerciseCard(exercise = exercise)
                    }
                }
            } else {
                val gridState = rememberLazyGridState()
                LazyVerticalGrid(
                    state = gridState,
                    columns = GridCells.Fixed(2)
                ) {
                    items(state.muscleGroups) { muscleGroup ->
                        ExerciseMuscleGroupCard(
                            muscleGroupName = muscleGroup,
                            onClick = { /* TODO: Handle click */ }
                        )
                    }
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
            .padding(14.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(15.dp)
    ) {
        Column (horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp)) {
            Text(
                text = muscleGroupName,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Exercise Image",
                modifier = Modifier
                    .size(80.dp)
            )
        }
    }
}

@Composable
fun ExerciseCard(exercise: Exercise) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { /* TODO: Handle click */ },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Exercise Image",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = exercise.name ?: "Undefined",
            style = MaterialTheme.typography.titleMedium
        )
    }
}