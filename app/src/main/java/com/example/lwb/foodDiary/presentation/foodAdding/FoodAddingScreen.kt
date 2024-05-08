package com.example.lwb.foodDiary.presentation.foodAdding

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.lwb.core.data.entities.Product
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun FoodAddingScreen(
    viewModel: FoodAddingViewModel = hiltViewModel()
){
    val state by viewModel.state.collectAsState()
    val products by viewModel.products.collectAsState()
    val productsByName by viewModel.productsByName.collectAsState()
    var visible by remember { mutableStateOf(false) }

    if (!visible) {
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
                        ProductCard(product = product, viewModel)
                    }
                }
            } else {
                LazyColumn {
                    items(productsByName) { productByName ->
                        ProductCard(product = productByName, viewModel)
                    }
                }
            }
        }
        if (state.chosenProducts.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.95f),
                contentAlignment = Alignment.BottomCenter
            ) {
                Button(
                    onClick = { visible = !visible },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ),
                    border = BorderStroke(width = 1.dp, color = Color.Black)
                ) {
                    Text(text = "Добавить")
                }
            }
        }
    }
    if (visible)
    Box( modifier = Modifier
        .fillMaxWidth()
        .background(Color.White)
    ){
        val meals = listOf("Перекус", "Завтрак", "Обед", "Ужин")
        val (selectedOption, onOptionSelected) = remember { mutableStateOf(meals[0]) }
        Column {
            Text(text = "Выберите прием пищи", fontSize = 28.sp, modifier = Modifier.padding(10.dp))
            Column(Modifier.selectableGroup()) {
                meals.forEach { text ->
                    Row( modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .clickable {
                                onOptionSelected(text)
                                viewModel.onEvent(FoodAddingEvent.OnChooseEating(text))
                                       }
                            .border(
                                width = 1.dp,
                                color = if (text == selectedOption) Color.Black else Color.White)
                            .background(if (text == selectedOption) Color.LightGray else Color.White),
                         verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        Text( text = text, fontSize = 24.sp )
                    }
                }
            }
            Row( modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.95f),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center
            ){
                Button(onClick = { },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black),
                    border = BorderStroke(width = 1.dp, color = Color.Black)
                ) {
                    Text(text = "Сохранить")
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductCard(product: Product, viewModel: FoodAddingViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { viewModel.onEvent(FoodAddingEvent.OnProductClick(product)) }
            .border(
                width = 1.dp,
                color = if (viewModel.state.value.chosenProducts.contains(product)) Color.Black else Color.White
            ),
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

