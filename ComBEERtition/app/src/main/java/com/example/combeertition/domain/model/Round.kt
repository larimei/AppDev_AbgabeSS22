package com.example.combeertition.domain.model

import androidx.compose.ui.graphics.Color
import java.time.ZonedDateTime

@JvmInline
value class RoundId(val value: String)


class Round private constructor(
    val id: RoundId,
    val round: String,
    val firstTeam: String,
    val secondTeam: String,
    val winner: String?,
    val loser: String?,
    val created: ZonedDateTime,
    val updated: ZonedDateTime,
    val deleted: ZonedDateTime,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Round

        if (id != other.id) return false

        return true
    }
    override fun hashCode(): Int {
        return id.hashCode()
    }

    companion object {
        fun create(
            id: RoundId,
            round: String,
            firstTeam: String,
            secondTeam: String,
            winner: String?,
            loser: String?,
        ): Round {
            val now = ZonedDateTime.now()
            return Round(id, round, firstTeam, secondTeam, winner, loser, now, now, now)
        }
    }
}
