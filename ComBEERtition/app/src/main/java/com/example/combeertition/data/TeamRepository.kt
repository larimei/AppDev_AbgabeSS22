package com.example.combeertition.data

import androidx.compose.ui.graphics.Color
import com.example.combeertition.R
import com.example.combeertition.domain.model.Team
import com.example.combeertition.domain.model.TeamId

val teamRepository = TeamRepository()

class TeamRepository {
    private var allTeams = listOfNotNull(
        Team.create(
            id = TeamId("at"),
            name = "Team 1",
            icon = R.drawable.ic_team,
            color = Color.Red,
            players = listOf("a")
        ),
        Team.create(
            id = TeamId("bt"),
            name = "Team 2",
            icon = R.drawable.ic_team,
            color = Color.Red,
            players = emptyList(),
        ),
        Team.create(
            id = TeamId("ct"),
            name = "Team 3",
            icon = R.drawable.ic_team,
            color = Color.Red,
            players = emptyList(),
        ),
        Team.create(
            id = TeamId("dt"),
            name = "Team 4",
            icon = R.drawable.ic_team,
            color = Color.Red,
            players = emptyList(),
        ),
        Team.create(
            id = TeamId("et"),
            name = "Team 5",
            icon = R.drawable.ic_team,
            color = Color.Red,
            players = emptyList(),
        ),
        Team.create(
            id = TeamId("ft"),
            name = "Team 6",
            icon = R.drawable.ic_team,
            color = Color.Red,
            players = emptyList(),
        ),
    )

    fun getAllTeams() = allTeams

    fun getTeamById(id: TeamId): Team? = allTeams.firstOrNull {
        it.id == id
    }

    fun updateTeams(newTeams: List<Team>): List<Team> {
        allTeams = newTeams
        return getAllTeams()
    }

}