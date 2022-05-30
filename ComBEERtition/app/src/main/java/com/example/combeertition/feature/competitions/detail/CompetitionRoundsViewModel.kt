package com.example.combeertition.feature.competitions.detail

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@Immutable
data class ExpandableCardModel(val id: Int, val title: String)

class CompetitionRoundsViewModel: ViewModel() {
    private val _rounds = MutableStateFlow(listOf<ExpandableCardModel>())
    val rounds: StateFlow<List<ExpandableCardModel>> get() = _rounds

    private val _expandedCardIdsList = MutableStateFlow(listOf<Int>())
    val expandedCardIdsList: StateFlow<List<Int>> get() = _expandedCardIdsList

    init {
        getFakeData()
    }

    private fun getFakeData() {
        viewModelScope.launch(Dispatchers.Default) {
            val testList = arrayListOf<ExpandableCardModel>()
            repeat(20) { testList += ExpandableCardModel(id = it, title = "Card $it") }
            _rounds.emit(testList)
        }
    }

    fun onCardArrowClicked(cardId: Int) {
        _expandedCardIdsList.value = _expandedCardIdsList.value.toMutableList().also { list ->
            if (list.contains(cardId)) list.remove(cardId) else list.add(cardId)
        }
    }
}