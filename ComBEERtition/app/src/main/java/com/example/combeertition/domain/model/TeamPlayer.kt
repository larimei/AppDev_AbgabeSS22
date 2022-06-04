package com.example.combeertition.domain.model

import androidx.compose.ui.graphics.Color
import java.time.ZonedDateTime

@JvmInline
value class TeamPlayerId(val value: String)


class TeamPlayer private constructor(
    val id: TeamPlayerId,
    val teamId: TeamId,
    val playerId: PlayerId,
    val created: ZonedDateTime,
    val updated: ZonedDateTime,
    val deleted: ZonedDateTime,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TeamPlayer

        if (id != other.id) return false

        return true
    }
    override fun hashCode(): Int {
        return id.hashCode()
    }

    companion object {
        fun create(
            id: TeamPlayerId,
            teamId: TeamId,
            playerId: PlayerId

        ): TeamPlayer {
            val now = ZonedDateTime.now()
            return TeamPlayer(id, teamId, playerId, now, now, now)
        }
    }
}

