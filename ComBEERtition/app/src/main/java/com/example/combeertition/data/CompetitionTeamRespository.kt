package com.example.combeertition.data

import com.example.combeertition.App
import com.example.combeertition.data.database.relations.competitionteam.CompetitionTeamDao
import com.example.combeertition.data.database.relations.competitionteam.competitionTeamToDb
import com.example.combeertition.data.database.relations.competitionteam.competitionTeamFromDb
import com.example.combeertition.data.database.relations.teamplayer.TeamPlayerDao
import com.example.combeertition.domain.model.*

val competitionTeamRepository = CompetitionTeamRepository(App.database.competitionTeamDao())

class CompetitionTeamRepository
    (private val dao: CompetitionTeamDao) {

    suspend fun getCompetitionTeamPlayers(): List<CompetitionTeam> = dao.getAll().mapNotNull { competitionTeamFromDb(it) }

    suspend fun getCompetitionTeamById(id: CompetitionTeamId): CompetitionTeam? =
        dao.getById(id.value)?.let { competitionTeamFromDb(it) }

    suspend fun getByCompetitionId(id: CompetitionId): List<CompetitionTeam> =
        dao.getByCompetitionId(id.value).mapNotNull { competitionTeamFromDb(it) }

    suspend fun getByTeamId(id: TeamId): List<CompetitionTeam> =
        dao.getByTeamId(id.value).mapNotNull { competitionTeamFromDb(it) }

    suspend fun addCompetitionTeam(competitionTeam: CompetitionTeam) {
        dao.insert(competitionTeamToDb(competitionTeam))
    }

    suspend fun deleteCompetitionTeam(competitionTeam: CompetitionTeam) {
        dao.delete(competitionTeamToDb(competitionTeam))
    }
}