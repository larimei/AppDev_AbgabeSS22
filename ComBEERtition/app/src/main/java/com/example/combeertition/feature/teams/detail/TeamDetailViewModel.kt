package com.example.combeertition.feature.teams.detail

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.combeertition.domain.model.Team
import com.example.combeertition.domain.model.TeamId
import com.example.combeertition.domain.team.AddTeamsUseCase
import com.example.combeertition.domain.team.UpdateTeamUseCase
import com.example.combeertition.feature.main.ui.navControllerGlobal

class TeamDetailViewModel: ViewModel() {

    fun onAddTeams(count: Int, players: List<String>, random: Boolean) {
        AddTeamsUseCase()(count, players, random)
    }

    fun onUpdateTeam(teamId: TeamId, name: String, color: Color, players: List<String>) {
        UpdateTeamUseCase()(teamId, name, color, players)
    }
}