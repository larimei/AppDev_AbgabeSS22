package com.example.combeertition.feature.competitions.detail

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.combeertition.domain.competition.AddCompetitionUseCase
import com.example.combeertition.domain.competition.GetCompetitionByIdUseCase
import com.example.combeertition.domain.competition.UpdateCompetitionUseCase
import com.example.combeertition.domain.model.Competition
import com.example.combeertition.domain.model.CompetitionId
import com.example.combeertition.domain.rounds.CreateRoundsUseCase
import com.example.combeertition.feature.main.ui.navControllerGlobal
import kotlinx.coroutines.launch

class CompetitionDetailViewModel : ViewModel() {

    fun bindUI(context: Context, competitionId: CompetitionId): LiveData<Competition?> = liveData {
        val state = GetCompetitionByIdUseCase()(competitionId)
        emit(state)
    }

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
        navControllerGlobal?.navigate("competitions")
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