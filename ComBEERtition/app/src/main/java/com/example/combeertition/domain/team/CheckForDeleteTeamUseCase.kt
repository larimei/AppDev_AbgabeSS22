package com.example.combeertition.domain.team

import com.example.combeertition.data.*
import com.example.combeertition.domain.model.TeamId

class CheckForDeleteTeamUseCase() {
    suspend operator fun invoke(teamId: TeamId): Boolean {
        return competitionTeamRepository.getByTeamId(teamId).count() <= 0
    }
}