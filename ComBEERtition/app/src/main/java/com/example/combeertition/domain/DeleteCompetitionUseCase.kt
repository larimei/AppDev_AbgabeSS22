package com.example.combeertition.domain

import com.example.combeertition.data.competitionRepository
import com.example.combeertition.domain.model.CompetitionId

class DeleteCompetitionUseCase {

    operator fun invoke(competitionId: CompetitionId): Boolean {
        val competition = competitionRepository.getCompetitionById(competitionId) ?: return false

        val updatedCompetitions = competitionRepository.getAllCompetitions().filter{it != competition}
        competitionRepository.updateCompetitions(updatedCompetitions)
        return true
    }
}
