package com.example.combeertition.domain.team

import com.example.combeertition.data.teamRepository
import com.example.combeertition.domain.model.Team
import com.example.combeertition.domain.model.TeamId

class GetTeamByIdUseCase {
    suspend operator fun invoke(teamId: TeamId): Team? {
        return teamRepository.getTeamById(teamId)
    }
}