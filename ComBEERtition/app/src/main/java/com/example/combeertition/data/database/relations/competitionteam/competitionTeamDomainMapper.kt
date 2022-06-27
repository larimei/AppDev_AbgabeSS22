package com.example.combeertition.data.database.relations.competitionteam

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.combeertition.data.database.player.PlayerDb
import com.example.combeertition.domain.model.*

fun competitionTeamToDb(competitionTeam: CompetitionTeam): CompetitionTeamDb = CompetitionTeamDb(
    competitionTeamId = competitionTeam.id.value,
    competitionId = competitionTeam.competitionId.value,
    teamId = competitionTeam.teamId.value,
    created = competitionTeam.created,
    updated = competitionTeam.updated,
    deleted = competitionTeam.deleted,
)

fun competitionTeamFromDb(competitionTeam: CompetitionTeamDb): CompetitionTeam? {
    return CompetitionTeam.create(
        id = CompetitionTeamId(competitionTeam.competitionTeamId),
        competitionId = CompetitionId(competitionTeam.competitionId),
        teamId = TeamId(competitionTeam.teamId),

    )
}