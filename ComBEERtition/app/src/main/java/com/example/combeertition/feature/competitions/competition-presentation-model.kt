package com.example.combeertition.feature.competition

import androidx.annotation.DrawableRes
import com.example.combeertition.domain.model.CompetitionId

class CompetitionsScreenUI(
    val competitions: List<CompetitionUI>,
)


class CompetitionUI(
    val id: CompetitionId,
    val name: String,
    @DrawableRes val icon: Int,
)
