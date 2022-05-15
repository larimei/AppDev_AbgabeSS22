package com.example.combeertition.domain.team

import com.example.combeertition.data.playerRepository
import com.example.combeertition.data.teamRepository
import com.example.combeertition.domain.model.Player
import com.example.combeertition.domain.model.Team

class AddTeamUseCase {
    operator fun invoke(team: Team): Boolean {
        val updatedTeams = teamRepository.getAllTeams().plus(team)
        teamRepository.updateTeams(updatedTeams)
        return true
    }
}