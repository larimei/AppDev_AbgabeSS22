package com.example.combeertition.feature.player.detail

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.example.combeertition.domain.model.PlayerId

class PlayerDetailUI(
    val id: PlayerId,
    val name: String,
    val color: Color,
    val wins: Int,
    val looses: Int,
    val matches: Int
)
