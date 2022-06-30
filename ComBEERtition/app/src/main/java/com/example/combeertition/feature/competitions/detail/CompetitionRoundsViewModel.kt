package com.example.combeertition.feature.competitions.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.combeertition.data.ExpandableCardModel
import com.example.combeertition.domain.model.*
import com.example.combeertition.domain.rounds.UpdateRoundUseCase
import com.example.combeertition.domain.rounds.GetRoundsAsCardsUseCase
import com.example.combeertition.domain.rounds.SetWinnerToRoundUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class CompetitionRoundsViewModel : ViewModel() {

    private val _expandedCardIdsList = MutableStateFlow(listOf<Int>())
    val expandedCardIdsList: StateFlow<List<Int>> get() = _expandedCardIdsList

    fun onCreateCards(competitionId: CompetitionId): LiveData<List<ExpandableCardModel>> = liveData {
        emit(GetRoundsAsCardsUseCase()(competitionId))
    }

    fun onCardArrowClicked(cardId: Int) {
        _expandedCardIdsList.value = _expandedCardIdsList.value.toMutableList().also { list ->
            if (list.contains(cardId)) list.remove(cardId) else list.add(cardId)
        }
    }

    fun onEditRound(roundId: RoundId, pointsFirst: Int, pointsSecond: Int) {
        viewModelScope.launch {
            UpdateRoundUseCase(SetWinnerToRoundUseCase())(roundId, pointsFirst, pointsSecond)
        }
    }
}