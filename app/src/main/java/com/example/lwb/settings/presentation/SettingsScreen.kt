package com.example.lwb.settings.presentation

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun SettingsScreen(
    onSignOut: () -> Unit
){
    Button(onClick = onSignOut) {
        Text(text = "Выход")
    }
}