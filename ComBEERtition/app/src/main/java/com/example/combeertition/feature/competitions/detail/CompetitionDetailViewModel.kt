package com.example.combeertition.feature.competitions.detail

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.combeertition.domain.competition.AddCompetitionUseCase
import com.example.combeertition.domain.competition.UpdateCompetitionUseCase
import com.example.combeertition.domain.model.CompetitionId
import com.example.combeertition.domain.rounds.CreateRoundsUseCase
import com.example.combeertition.feature.main.ui.navControllerGlobal
import kotlinx.coroutines.launch

class CompetitionDetailViewModel : ViewModel() {

    fun onAddCompetition(
        competitionId: CompetitionId,
        name: String,
        color: Color,
        teams: List<String>,
        mode: String
    ) {
        viewModelScope.launch {
            AddCompetitionUseCase(CreateRoundsUseCase())(competitionId, name, color, teams, mode)
        }
        navControllerGlobal?.navigate("competition/" + competitionId.value)
    }

    fun onUpdateCompetition(
        competitionId: CompetitionId,
        name: String,
        color: Color,
        teams: List<String>,
        mode: String,
    ) {
        viewModelScope.launch {
            UpdateCompetitionUseCase(CreateRoundsUseCase())(competitionId, name, color, teams, mode)
        }
    }
}