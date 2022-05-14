package com.example.combeertition.domain.model

import java.time.ZonedDateTime

@JvmInline
value class TeamId(val value: String)


class Team private constructor(
    val id: TeamId,
    val name: String,
    val icon: Int,
    val players: List<String>,
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
            players: List<String>
        ): Team? {
            if (name.isBlank()) return null
            val now = ZonedDateTime.now()
            return Team(id, name, icon, players, now, now, now)
        }
    }
}

