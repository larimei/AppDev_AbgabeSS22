package com.example.combeertition.data.database.player

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.combeertition.domain.model.Player
import com.example.combeertition.domain.model.PlayerId

fun playerToDb(player: Player): PlayerDb = PlayerDb(
    playerId = player.id.value,
    name = player.name,
    icon = player.icon,
    color = player.color.toArgb(),
    wins = player.wins,
    looses = player.looses,
    matches = player.matches,
    created = player.created,
    updated = player.updated,
    deleted = player.deleted,
)

fun playerFromDb(player: PlayerDb): Player? {
    return Player.create(
        id = PlayerId(player.playerId),
        name = player.name,
        icon = player.icon,
        color = Color(player.color),
        wins = player.wins,
        looses = player.looses,
        matches = player.matches
    )
}
