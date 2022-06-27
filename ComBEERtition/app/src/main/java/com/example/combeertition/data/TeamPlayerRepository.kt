package com.example.combeertition.data

import com.example.combeertition.App
import com.example.combeertition.data.database.relations.teamplayer.teamPlayerToDb
import com.example.combeertition.data.database.relations.teamplayer.teamPlayerFromDb
import com.example.combeertition.data.database.relations.teamplayer.TeamPlayerDao
import com.example.combeertition.domain.model.*

val teamPlayerRepository = TeamPlayerRepository(App.database.teamPlayerDao())

class TeamPlayerRepository
    (private val dao: TeamPlayerDao) {

    suspend fun getAllTeamPlayers(): List<TeamPlayer> = dao.getAll().mapNotNull { teamPlayerFromDb(it) }

    suspend fun getTeamPlayerById(id: TeamPlayerId): TeamPlayer? =
        dao.getById(id.value)?.let { teamPlayerFromDb(it) }


    suspend fun getByTeamId(id: TeamId): List<TeamPlayer> =
        dao.getByTeamId(id.value).mapNotNull { teamPlayerFromDb(it) }

    suspend fun getByPlayerId(id: PlayerId): List<TeamPlayer> =
        dao.getByPlayerId(id.value).mapNotNull { teamPlayerFromDb(it) }

    suspend fun addTeamPlayer(teamPlayer: TeamPlayer) {
        dao.insert(teamPlayerToDb(teamPlayer))
    }

    suspend fun deleteTeamPlayer(teamPlayer: TeamPlayer) {
        dao.delete(teamPlayerToDb(teamPlayer))
    }
}