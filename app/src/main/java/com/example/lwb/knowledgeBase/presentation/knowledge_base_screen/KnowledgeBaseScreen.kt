package com.example.lwb.knowledgeBase.presentation.knowledge_base_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.lwb.R
import com.example.lwb.knowledgeBase.presentation.knowledge_base_screen.components.KnowledgeBaseSectionCard
import androidx.navigation.NavController

@Composable
fun KnowledgeBaseScreen(
    navController: NavController
){
    Column(
        Modifier.fillMaxSize()
    ) {
        KnowledgeBaseSectionCard(title = "Продукты", description = "Узнайте все о \nпродуктах \nпитания", imageId = R.drawable.food, destination = "exercisePage", navController = navController)
        KnowledgeBaseSectionCard(title = "Упражнения", description = "Изучите упражнения и технику их выполнения", imageId = R.drawable.exercise, destination = "exercisePage", navController = navController)
    }
}