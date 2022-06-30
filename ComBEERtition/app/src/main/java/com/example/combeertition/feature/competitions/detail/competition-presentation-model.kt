package com.example.combeertition.feature.competitions.detail

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.example.combeertition.domain.model.CompetitionId

class CompetitionDetailUI(
    val id: CompetitionId,
    val name: String,
    val color: Color,
    val mode: String,
    val teams: List<String>,
)
