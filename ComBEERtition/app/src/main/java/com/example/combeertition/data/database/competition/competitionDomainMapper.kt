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
    competitionTeamRoundRelation: CompetitionWithTeamsAndRounds,
): Competition? {
    return Competition.create(
        id = CompetitionId(competitionTeamRoundRelation.competition.competition.competitionId),
        name = competitionTeamRoundRelation.competition.competition.name,
        icon = competitionTeamRoundRelation.competition.competition.icon,
        teams = competitionTeamRoundRelation.competition.teams.mapNotNull { it.teamId },
        mode = competitionTeamRoundRelation.competition.competition.mode,
        rounds = competitionTeamRoundRelation.rounds.mapNotNull { it.roundId },
        color = Color(competitionTeamRoundRelation.competition.competition.color)
    )
}