package com.example.combeertition.domain.model

import androidx.compose.ui.graphics.Color
import java.time.ZonedDateTime

@JvmInline
value class CompetitionTeamId(val value: String)


class CompetitionTeam private constructor(
    val id: CompetitionTeamId,
    val competitionId: CompetitionId,
    val teamId: TeamId,
    val created: ZonedDateTime,
    val updated: ZonedDateTime,
    val deleted: ZonedDateTime,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CompetitionTeam

        if (id != other.id) return false

        return true
    }
    override fun hashCode(): Int {
        return id.hashCode()
    }

    companion object {
        fun create(
            id: CompetitionTeamId,
            competitionId: CompetitionId,
            teamId: TeamId

        ): CompetitionTeam {
            val now = ZonedDateTime.now()
            return CompetitionTeam(id, competitionId, teamId, now, now, now)
        }
    }
}

