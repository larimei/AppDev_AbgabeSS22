package com.example.combeertition.data

import androidx.compose.ui.graphics.Color
import com.example.combeertition.App
import com.example.combeertition.R
import com.example.combeertition.data.database.team.teamFromDb
import com.example.combeertition.data.database.team.TeamDao
import com.example.combeertition.data.database.team.teamToDb
import com.example.combeertition.domain.model.Team
import com.example.combeertition.domain.model.TeamId

val teamRepository = TeamRepository(App.database.teamDao())

class TeamRepository (private val dao: TeamDao) {

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