package com.example.combeertition.data.database.relations.teamplayer

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.combeertition.data.database.player.PlayerDb
import com.example.combeertition.domain.model.*

fun teamPlayerToDb(teamPlayer: TeamPlayer): TeamPlayerDb = TeamPlayerDb(
    teamPlayerId = teamPlayer.id.value,
    teamId = teamPlayer.teamId.value,
    playerId = teamPlayer.playerId.value,
    created = teamPlayer.created,
    updated = teamPlayer.updated,
    deleted = teamPlayer.deleted,
)

fun teamPlayerFromDb(teamPlayer: TeamPlayerDb): TeamPlayer? {
    return TeamPlayer.create(
        id = TeamPlayerId(teamPlayer.teamPlayerId),
        teamId = TeamId(teamPlayer.teamId),
        playerId = PlayerId(teamPlayer.playerId)

    )
}