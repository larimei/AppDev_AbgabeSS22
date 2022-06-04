package com.example.combeertition.feature.teams.detail

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.combeertition.domain.model.Player
import com.example.combeertition.domain.model.PlayerId
import com.example.combeertition.domain.model.Team
import com.example.combeertition.domain.model.TeamId
import com.example.combeertition.domain.player.AddPlayerUseCase
import com.example.combeertition.domain.player.GetPlayerByIdUseCase
import com.example.combeertition.domain.player.UpdatePlayerUseCase
import com.example.combeertition.domain.team.AddTeamsUseCase
import com.example.combeertition.domain.team.DeleteTeamUseCase
import com.example.combeertition.domain.team.GetTeamByIdUseCase
import com.example.combeertition.domain.team.UpdateTeamUseCase
import com.example.combeertition.feature.main.ui.navControllerGlobal
import kotlinx.coroutines.launch

class TeamDetailViewModel : ViewModel() {

    fun bindUI(context: Context, teamId: TeamId): LiveData<Team?> = liveData {
        val state = GetTeamByIdUseCase()(teamId)
        emit(state)
    }


    fun onAddTeams(count: Int, players: List<String>, random: Boolean) {
        viewModelScope.launch {
            AddTeamsUseCase()(count, players, random)
            navControllerGlobal?.navigate("teams")
        }
    }

    fun onUpdateTeam(
        teamId: TeamId,
        name: String,
        color: Color,
        players: List<String>,
        wins: Int,
        looses: Int,
        matches: Int
    ) {
        viewModelScope.launch {
            UpdateTeamUseCase()(teamId, name, color, players, wins, looses, matches)
        }
    }

    fun onDeleteTeam(teamId: TeamId) {
        viewModelScope.launch {
            GetTeamByIdUseCase()(teamId)?.let { DeleteTeamUseCase()(it) }
        }
    }
}