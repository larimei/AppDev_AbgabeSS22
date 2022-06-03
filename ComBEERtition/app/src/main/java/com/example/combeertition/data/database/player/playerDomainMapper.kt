package com.example.combeertition.data.database.player

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.combeertition.domain.model.Player
import com.example.combeertition.domain.model.PlayerId

fun playerToDb(player: Player): PlayerDB = PlayerDB(
    playerId = player.id.value,
    name = player.name,
    icon = player.icon,
    color = player.color.toArgb(),
    created = player.created,
    updated = player.updated,
    deleted = player.deleted,
)

fun playerFromDb(player: PlayerDB): Player? {
    return Player.create(
        id = PlayerId(player.playerId),
        name = player.name,
        icon = player.icon,
        color = Color(player.color)
    )
}
