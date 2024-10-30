package com.example.pagination

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UserProfileCard(name: String, age: Int, position: String, status: String) {
    Card(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .statusBarsPadding()
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F7FA))
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = "Name: $name", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text(text = "Age: $age", fontSize = 16.sp)
            Text(text = "Position: $position", fontSize = 16.sp)
            Text(text = "Status: $status", fontSize = 16.sp, color = if (status == "Active") Color.Green else Color.Red)
        }
    }
}