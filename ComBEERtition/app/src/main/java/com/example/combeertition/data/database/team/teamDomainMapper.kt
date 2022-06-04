package com.example.combeertition.data.database.team

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.combeertition.domain.model.Team
import com.example.combeertition.domain.model.TeamId

fun teamToDb(team: Team): TeamDb = TeamDb(
    teamId = team.id.value,
    name = team.name,
    icon = team.icon,
    color = team.color.toArgb(),
    wins = team.wins,
    looses = team.looses,
    matches = team.matches,
    created = team.created,
    updated = team.updated,
    deleted = team.deleted,
)

fun teamFromDb(teamPlayerRelation: TeamWithPlayers): Team? {
    return Team.create(
        id = TeamId(teamPlayerRelation.team.teamId),
        name = teamPlayerRelation.team.name,
        icon = teamPlayerRelation.team.icon,
        color = Color(teamPlayerRelation.team.color),
        players = teamPlayerRelation.players.mapNotNull { it.playerId },
        wins = teamPlayerRelation.team.wins,
        looses = teamPlayerRelation.team.looses,
        matches = teamPlayerRelation.team.matches
    )
}