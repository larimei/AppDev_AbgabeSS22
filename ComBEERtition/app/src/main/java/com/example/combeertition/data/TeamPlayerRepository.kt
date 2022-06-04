package com.example.combeertition.data

import com.example.combeertition.App
import com.example.combeertition.data.database.relations.teamplayer.teamPlayerToDb
import com.example.combeertition.data.database.relations.teamplayer.teamPlayerFromDb
import com.example.combeertition.data.database.relations.teamplayer.TeamPlayerDao
import com.example.combeertition.domain.model.Player
import com.example.combeertition.domain.model.PlayerId
import com.example.combeertition.domain.model.TeamPlayer

//val teamPayerRepository = TeamPlayerRepository(App.database.teamPlayerDao())

class TeamPlayerRepository
    (private val dao: TeamPlayerDao) {

    suspend fun getAllTeamPlayers(): List<TeamPlayer> = dao.getAll().mapNotNull { teamPlayerFromDb(it) }

    suspend fun getTeamPlayerById(id: PlayerId): TeamPlayer? =
        dao.getById(id.value)?.let { teamPlayerFromDb(it) }

    suspend fun addTeamPlayer(teamPlayer: TeamPlayer) {
        dao.insert(teamPlayerToDb(teamPlayer))
    }

    suspend fun deleteTeamPlayer(teamPlayer: TeamPlayer) {
        dao.delete(teamPlayerToDb(teamPlayer))
    }
}