package com.example.lwb.foodDiary.presentation.foodAdding

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.lwb.R
import com.example.lwb.core.data.entities.Exercise
import com.example.lwb.core.data.entities.Product
import com.example.lwb.exerciseBase.presentation.exercisePage.ExerciseCard
import com.example.lwb.foodDiary.presentation.diary.DiaryViewModel
import com.bumptech.glide.integration.compose.GlideImage
import com.example.lwb.exerciseBase.presentation.exercisePage.ExercisePageEvent

@Composable
fun FoodAddingScreen(
    viewModel: FoodAddingViewModel = hiltViewModel()
){
    val state by viewModel.state.collectAsState()
    val products by viewModel.products.collectAsState()
    val productsByName by viewModel.productsByName.collectAsState()

    Column(
        modifier = Modifier
        .padding(5.dp)
        .fillMaxSize()
    ) {
        OutlinedTextField(
            value = state.searchQuery,
            onValueChange = { viewModel.onEvent(FoodAddingEvent.OnSearchQueryChange(it)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = { Text("Пoиск") }
        )
        if (state.searchQuery.isEmpty()) {
            LazyColumn {
                items(products) { product ->
                    ProductCard(product = product)
                }
            }
        } else {
            LazyColumn {
                items(productsByName) { productByName ->
                    ProductCard(product = productByName)
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductCard(product: Product) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { /* TODO: Handle click */ },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column() {
            GlideImage(
                model = product.image,
                contentDescription = "Product picture",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
        }
        Column() {
            Row() {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Row(){
                Text(
                text = "${product.calories}ккал     Белки: ${product.proteins}г.  Жиры: ${product.fats}г.  Углеводы: ${product.carbohydrates}г."
                )
            }
        }
    }
}

