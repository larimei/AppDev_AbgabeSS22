package com.example.combeertition.domain.team

import com.example.combeertition.data.playerRepository
import com.example.combeertition.data.teamRepository
import com.example.combeertition.domain.model.Team
import com.example.combeertition.domain.model.TeamId

class DeleteTeamUseCase {
    suspend operator fun invoke(team: Team): Boolean {
        teamRepository.deleteTeam(team)
        return true
    }
}
