package com.example.combeertition.feature.teams.detail

import androidx.compose.ui.graphics.Color
import com.example.combeertition.domain.model.TeamId

class TeamDetailUI(
    val id: TeamId,
    val name: String,
    val color: Color,
    val wins: Int,
    val looses: Int,
    val matches: Int,
    val players: List<String>,
)