package com.example.combeertition.data

import androidx.compose.ui.graphics.Color
import com.example.combeertition.App
import com.example.combeertition.R
import com.example.combeertition.data.database.team.teamFromDb
import com.example.combeertition.data.database.player.playerFromDb
import com.example.combeertition.data.database.player.playerToDb
import com.example.combeertition.data.database.team.TeamDao
import com.example.combeertition.data.database.team.teamToDb
import com.example.combeertition.domain.model.Player
import com.example.combeertition.domain.model.PlayerId
import com.example.combeertition.domain.model.Team
import com.example.combeertition.domain.model.TeamId

val teamRepository = TeamRepository(App.database.teamDao())

class TeamRepository (private val dao: TeamDao) {
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


    suspend fun getAllTeams(): List<Team> = dao.getAll().mapNotNull { teamFromDb(it) }

    suspend fun getTeamById(id: TeamId): Team? =
        dao.getById(id.value)?.let { teamFromDb(it) }

    suspend fun addTeam(team: Team) {
        dao.insert(teamToDb(team))
    }

    suspend fun deleteTeam(team: Team) {
        dao.delete(teamToDb(team))
    }

    suspend fun updateTeam(team: Team) {
        dao.update(teamToDb(team))
    }
}