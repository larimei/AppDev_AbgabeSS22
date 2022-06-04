package com.example.combeertition.domain.model

import androidx.compose.ui.graphics.Color
import java.time.ZonedDateTime

@JvmInline
value class TeamId(val value: String)


class Team private constructor(
    val id: TeamId,
    val name: String,
    val icon: Int,
    val color: Color,
    val wins: Int,
    val looses: Int,
    val matches: Int,
    var players: List<String>,
    val created: ZonedDateTime,
    val updated: ZonedDateTime,
    val deleted: ZonedDateTime,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Team

        if (id != other.id) return false

        return true
    }
    override fun hashCode(): Int {
        return id.hashCode()
    }

    companion object {
        fun create(
            id: TeamId,
            name: String,
            icon: Int,
            color: Color,
            players: List<String>,
            wins: Int,
            looses: Int,
            matches: Int
        ): Team {
            val now = ZonedDateTime.now()
            return Team(id, name, icon, color, wins, looses, matches, players, now, now, now)
        }
    }
}

