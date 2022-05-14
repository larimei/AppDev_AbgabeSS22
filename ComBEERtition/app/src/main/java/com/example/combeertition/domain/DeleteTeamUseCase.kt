package com.example.combeertition.domain

import com.example.combeertition.data.teamRepository
import com.example.combeertition.domain.model.TeamId

class DeleteTeamUseCase {

    operator fun invoke(teamId: TeamId): Boolean {
        val team = teamRepository.getTeamById(teamId) ?: return false

        val updatedTeams = teamRepository.getAllTeams().filter{it != team}
        teamRepository.updateTeams(updatedTeams)
        return true
    }
}
