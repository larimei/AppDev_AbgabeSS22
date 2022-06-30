package com.example.combeertition.domain.model

import androidx.compose.ui.graphics.Color
import java.time.ZonedDateTime

@JvmInline
value class CompetitionId(val value: String)


class Competition private constructor(
    val id: CompetitionId,
    val name: String,
    val teams: List<String>,
    val mode: String,
    val rounds: List<String>,
    val color: Color,
    val created: ZonedDateTime,
    val updated: ZonedDateTime,
    val deleted: ZonedDateTime,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Competition

        if (id != other.id) return false

        return true
    }
    override fun hashCode(): Int {
        return id.hashCode()
    }

    companion object {
        fun create(
            id: CompetitionId,
            name: String,
            teams: List<String>,
            mode: String,
            rounds: List<String>,
            color: Color
        ): Competition {
            val now = ZonedDateTime.now()
            return Competition(id, name, teams, mode, rounds, color, now, now, now)
        }
    }
}
