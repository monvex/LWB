package com.example.lwb.onboarding.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lwb.onboarding.presentation.OnBoardingState

@Composable
fun TextFieldWithTitleAbove(title: String, onValueChange: (String) -> Unit, text: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth() ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title ,
            color = Color.White ,
            fontSize = 24.sp ,
            textAlign = TextAlign.Center ,
            lineHeight = 16.sp ,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 25.dp)
        )
        OutlinedTextField(
            value = text,
            onValueChange = onValueChange,
            textStyle = TextStyle(fontSize = 30.sp, color = Color.White) ,
            modifier = Modifier
                .height(55.dp)
                .fillMaxWidth(0.3f)
                .border(BorderStroke(1.dp , Color.White) , RoundedCornerShape(10.dp)),
            singleLine = true,
            shape = RoundedCornerShape(5.dp)
        )
    }
}
