package com.example.combeertition.feature.player

import androidx.annotation.DrawableRes
import com.example.combeertition.domain.model.PlayerId

class PlayersScreenUI(
    val products: List<PlayerUI>,
)


class PlayerUI(
    val id: PlayerId,
    val name: String,
    @DrawableRes val icon: Int,
)
