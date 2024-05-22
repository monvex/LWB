package com.example.lwb.foodBase.presentation.foodDetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.lwb.core.data.entities.Product
import com.example.lwb.core.presentation.components.BackButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodDetailsScreen(foodId: Int,
                      navController: NavController,
                      viewModel: FoodDetailsViewModel = hiltViewModel()) {
    val food = remember { mutableStateOf<Product?>(null) }

    LaunchedEffect(foodId) {
        food.value = viewModel.getFoodById(foodId)
    }

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
                        text = food.value?.name.toString() ?: "Продукт",
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
        Image(
            painter = rememberAsyncImagePainter(model = food.value?.image),
            contentDescription = "Product Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .width(300.dp)
        )
        Text(text = "Калории:", modifier = Modifier.padding(top = 16.dp))
        Text(text = food.value?.calories.toString() ?: "Не определено")
        Text(text = "Белки:", modifier = Modifier.padding(top = 16.dp))
        Text(text = food.value?.proteins.toString() ?: "Не определено")
        Text(text = "Жиры:", modifier = Modifier.padding(top = 16.dp))
        Text(text = food.value?.fats.toString() ?: "Не определено")
        Text(text = "Углеводы:", modifier = Modifier.padding(top = 16.dp))
        Text(text = food.value?.carbohydrates.toString() ?: "Не определено")
        Spacer(modifier = Modifier.weight(1f))
    }
    }
}