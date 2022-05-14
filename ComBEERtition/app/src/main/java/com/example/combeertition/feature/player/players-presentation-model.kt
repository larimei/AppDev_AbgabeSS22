package com.example.combeertition.feature.player

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.example.combeertition.domain.model.PlayerId

class PlayersScreenUI(
    val players: List<PlayerUI>,
)


class PlayerUI(
    val id: PlayerId,
    val name: String,
    @DrawableRes val icon: Int,
    val color: Color
)
