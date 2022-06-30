package com.example.combeertition.data

import androidx.compose.ui.graphics.Color
import com.example.combeertition.App
import com.example.combeertition.R
import com.example.combeertition.data.database.competition.CompetitionDao
import com.example.combeertition.data.database.competition.competitionFromDb
import com.example.combeertition.data.database.competition.competitionToDb
import com.example.combeertition.domain.model.Competition
import com.example.combeertition.domain.model.CompetitionId

val competitionRepository = CompetitionRepository(App.database.competitionDao())

class CompetitionRepository(private val dao: CompetitionDao) {

    suspend fun getAllCompetitions(): List<Competition> =
        dao.getAll().mapNotNull { competitionFromDb(it) }

    suspend fun getCompetitionById(id: CompetitionId): Competition? =
        dao.getById(id.value)?.let { competitionFromDb(it) }

    suspend fun addCompetition(competition: Competition) {
        dao.insert(competitionToDb(competition))
    }

    suspend fun deleteCompetition(competition: Competition) {
        dao.delete(competitionToDb(competition))
    }

    suspend fun updateCompetition(competition: Competition) {
        dao.update(competitionToDb(competition))
    }

}