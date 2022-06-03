package com.example.combeertition.feature.competitions.detail

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.combeertition.data.ExpandableCardModel
import com.example.combeertition.domain.competition.AddCompetitionUseCase
import com.example.combeertition.domain.model.*
import com.example.combeertition.domain.rounds.EditRoundUseCase
import com.example.combeertition.domain.rounds.GetRoundsAsCardsUseCase
import com.example.combeertition.domain.team.GetTeamByIdUseCase
import com.example.combeertition.feature.main.ui.navControllerGlobal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class CompetitionRoundsViewModel : ViewModel() {
    private val _rounds = MutableStateFlow(listOf<ExpandableCardModel>())
    val rounds: StateFlow<List<ExpandableCardModel>> get() = _rounds

    private val _expandedCardIdsList = MutableStateFlow(listOf<Int>())
    val expandedCardIdsList: StateFlow<List<Int>> get() = _expandedCardIdsList

    fun onCreateCards(competitionId: CompetitionId) {
        viewModelScope.launch(Dispatchers.Default) {
            _rounds.emit(GetRoundsAsCardsUseCase()(competitionId))
        }
    }

    fun onCardArrowClicked(cardId: Int) {
        _expandedCardIdsList.value = _expandedCardIdsList.value.toMutableList().also { list ->
            if (list.contains(cardId)) list.remove(cardId) else list.add(cardId)
        }
    }

    fun onEditRound(roundId: RoundId, pointsFirst: Int, pointsSecond: Int) {
        EditRoundUseCase()(roundId, pointsFirst, pointsSecond)
    }
}