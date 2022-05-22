package com.example.combeertition.feature.competitions.detail

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.combeertition.R
import com.example.combeertition.domain.AddPlayerUseCase
import com.example.combeertition.domain.UpdatePlayerUseCase
import com.example.combeertition.domain.competition.AddCompetitionUseCase
import com.example.combeertition.domain.competition.UpdateCompetitionUseCase
import com.example.combeertition.domain.model.Competition
import com.example.combeertition.domain.model.CompetitionId
import com.example.combeertition.domain.model.Player
import com.example.combeertition.domain.model.PlayerId
import com.example.combeertition.domain.team.AddTeamsUseCase
import com.example.combeertition.feature.main.ui.navControllerGlobal

class CompetitionDetailViewModel : ViewModel() {

    fun onAddCompetition(competitionId: CompetitionId, name: String, color: Color, teams: List<String>, mode: String) {
        AddCompetitionUseCase()(competitionId, name, color, teams, mode)
        navControllerGlobal?.navigate("competition/" + competitionId.value)
    }

    fun onUpdateCompetition(
        competitionId: CompetitionId,
        name: String,
        color: Color,
        teams: List<String>,
        mode: String,
    ) {
        UpdateCompetitionUseCase()(competitionId, name, color, teams, mode )
    }
}