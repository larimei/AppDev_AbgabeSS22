package com.example.combeertition.domain.competition

import com.example.combeertition.data.competitionRepository
import com.example.combeertition.data.competitionTeamRepository
import com.example.combeertition.data.teamPlayerRepository
import com.example.combeertition.domain.model.Competition

class DeleteCompetitionUseCase {

    suspend operator fun invoke(competition: Competition): Boolean {
        for (team in competitionTeamRepository.getByCompetitionId(competition.id)) {
            competitionTeamRepository.deleteCompetitionTeam(team)
        }

        competitionRepository.deleteCompetition(competition)
        return true
    }
}
