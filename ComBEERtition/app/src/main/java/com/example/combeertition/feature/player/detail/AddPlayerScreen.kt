package com.example.combeertition.feature.player.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup

@Composable
fun AddPlayerScreen() {
        Box {
            val popupWidth = 200.dp
            val popupHeight = 50.dp
            val cornerSize = 16.dp

            Popup(alignment = Alignment.Center) {
                // Draw a rectangle shape with rounded corners inside the popup
                Box(
                    Modifier
                        .size(popupWidth, popupHeight)
                        .background(Color.White, RoundedCornerShape(cornerSize))
                )
            }
        }
    }
