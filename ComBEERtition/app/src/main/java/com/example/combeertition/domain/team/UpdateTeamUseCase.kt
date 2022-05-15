package com.example.combeertition.domain.team

import androidx.compose.ui.graphics.Color
import com.example.combeertition.R
import com.example.combeertition.data.playerRepository
import com.example.combeertition.data.teamRepository
import com.example.combeertition.domain.model.Player
import com.example.combeertition.domain.model.PlayerId
import com.example.combeertition.domain.model.Team
import com.example.combeertition.domain.model.TeamId

class UpdateTeamUseCase {
    operator fun invoke(teamId: TeamId, name: String, color: Color, players: List<String>): Boolean {
        val updatedTeam = Team.create(teamId, name, R.drawable.ic_team, color, players)
        val allTeams = teamRepository.getAllTeams()
        val newTeams = allTeams.mapNotNull { if (it.id == teamId) updatedTeam else it }
        teamRepository.updateTeams(newTeams)
        return true
    }
}