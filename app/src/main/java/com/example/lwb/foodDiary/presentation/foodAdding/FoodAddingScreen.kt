package com.example.lwb.foodDiary.presentation.foodAdding

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
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

@Composable
fun FoodAddingScreen(
    viewModel: FoodAddingViewModel = hiltViewModel()
){
    val products by viewModel.products.collectAsState()
    LazyColumn {
        items(products) { product ->
            ProductCard(product = product)
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
        GlideImage(
            model = product.image,
            contentDescription = "Product picture",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = product.name ?: "Undefined",
            style = MaterialTheme.typography.titleMedium
        )
    }
}

