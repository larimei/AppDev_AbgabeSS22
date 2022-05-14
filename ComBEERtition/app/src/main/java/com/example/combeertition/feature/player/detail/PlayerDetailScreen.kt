package com.example.combeertition.feature.player.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.combeertition.domain.model.Player
import com.example.combeertition.R

@Composable
fun PlayerDetailScreen(player: Player ) {
    var name by remember { mutableStateOf(player?.name) }
    var color by remember { mutableStateOf(player?.color) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            painter = painterResource(player?.icon),
            contentDescription = name,
            modifier = Modifier
                .size(80.dp)
                .padding(8.dp),
            tint = color
        )
        TextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier.padding(horizontal = 12.dp)
        )

    }
}