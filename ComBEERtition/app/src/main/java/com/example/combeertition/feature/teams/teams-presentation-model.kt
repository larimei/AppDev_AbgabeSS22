package com.example.combeertition.feature.teams

import androidx.annotation.DrawableRes
import com.example.combeertition.domain.model.TeamId

class TeamsScreenUI(
    val products: List<TeamUI>,
)


class TeamUI(
    val id: TeamId,
    val name: String,
    @DrawableRes val icon: Int,
)
