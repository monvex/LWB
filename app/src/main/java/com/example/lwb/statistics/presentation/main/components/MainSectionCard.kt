package com.example.lwb.statistics.presentation.main.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainSectionCard(
    title: String,
    description: String,
    imageId: Int,
    onClick: () -> Unit
) {
    Card(
        onClick =  onClick,
        modifier = Modifier
            .height(200.dp)
            .padding(horizontal = 32.dp , vertical = 20.dp),
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(2.dp, Color.Black)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.5f)
            ) {
                Text(
                    text = title ,
                    fontWeight = FontWeight.Bold ,
                    fontSize = 22.sp ,
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                )
                Text(
                    text = description,
                    lineHeight = 17.sp,
                    fontSize = 18.sp
                )
            }
            Image(painter = painterResource(id = imageId),
                contentDescription = "some_image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(1f)
                    .clip(shape = RoundedCornerShape(15.dp))
            )
        }
    }
}