package com.example.combeertition.feature.teams

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.example.combeertition.domain.model.TeamId

class TeamUI(
    val id: TeamId,
    val name: String,
    @DrawableRes val icon: Int,
    val color: Color
)
