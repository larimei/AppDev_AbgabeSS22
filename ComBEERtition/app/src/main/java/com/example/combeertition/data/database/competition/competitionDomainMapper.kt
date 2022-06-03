package com.example.combeertition.data.database.competition

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.combeertition.domain.model.*

fun competitionToDb(competition: Competition): CompetitionDb = CompetitionDb(
    competitionId = competition.id.value,
    name = competition.name,
    icon = competition.icon,
    color = competition.color.toArgb(),
    mode = competition.mode,
    created = competition.created,
    updated = competition.updated,
    deleted = competition.deleted,
)

fun competitionFromDb(
    competitionTeamRelation: CompetitionTeamRelation,
    competitionRoundRelation: CompetitionRoundRelation
): Competition? {
    return Competition.create(
        id = CompetitionId(competitionTeamRelation.competition.competitionId),
        name = competitionTeamRelation.competition.name,
        icon = competitionTeamRelation.competition.icon,
        teams = competitionTeamRelation.teams.mapNotNull { it.teamId },
        mode = competitionTeamRelation.competition.mode,
        rounds = competitionRoundRelation.rounds.mapNotNull { it.roundId },
        color = Color(competitionTeamRelation.competition.color)
    )
}