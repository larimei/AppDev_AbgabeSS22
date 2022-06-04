package com.example.combeertition.domain.competition

import com.example.combeertition.data.competitionRepository
import com.example.combeertition.domain.model.Competition
import com.example.combeertition.domain.model.CompetitionId

class GetCompetitionByIdUseCase {
    suspend operator fun invoke(competitionId: CompetitionId): Competition? {
        return competitionRepository.getCompetitionById(competitionId)
    }
}