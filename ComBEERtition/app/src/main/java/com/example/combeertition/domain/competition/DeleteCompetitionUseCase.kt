package com.example.combeertition.domain.competition

import com.example.combeertition.data.competitionRepository
import com.example.combeertition.domain.model.Competition

class DeleteCompetitionUseCase {

    suspend operator fun invoke(competition: Competition): Boolean {
        competitionRepository.deleteCompetition(competition)
        return true
    }
}
