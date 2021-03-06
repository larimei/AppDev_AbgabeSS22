package com.example.combeertition.feature.competitions

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.example.combeertition.domain.model.CompetitionId

class CompetitionUI(
    val id: CompetitionId,
    val name: String,
    val color: Color,
    val mode: String
)
