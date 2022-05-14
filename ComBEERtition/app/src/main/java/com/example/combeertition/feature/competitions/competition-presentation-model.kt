package com.example.combeertition.feature.competition

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.example.combeertition.domain.model.CompetitionId

class CompetitionsScreenUI(
    val competitions: List<CompetitionUI>,
)


class CompetitionUI(
    val id: CompetitionId,
    val name: String,
    @DrawableRes val icon: Int,
    val color: Color,
    val mode: String
)
