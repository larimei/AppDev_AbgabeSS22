package com.example.combeertition.domain.competition

import androidx.compose.ui.graphics.Color
import com.example.combeertition.R
import com.example.combeertition.data.competitionRepository
import com.example.combeertition.domain.model.*

class UpdateCompetitionUseCase() {
    operator fun invoke(competitionId: CompetitionId, name: String, color: Color, teams: List<String>, mode: String): Boolean {
        val rounds: List<String> = emptyList()
        val updatedCompetition = Competition.create(competitionId, name, R.drawable.ic_competition, teams, mode, rounds, color)
        val allCompetitions = competitionRepository.getAllCompetitions()
        val newCompetitions = allCompetitions.mapNotNull{if (it.id == competitionId) updatedCompetition else it}
        competitionRepository.updateCompetitions(newCompetitions)
        return true
    }
}