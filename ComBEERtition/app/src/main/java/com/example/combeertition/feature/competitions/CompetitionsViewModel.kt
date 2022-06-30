package com.example.combeertition.feature.competitions

import android.content.Context
import androidx.lifecycle.*
import com.example.combeertition.domain.competition.DeleteCompetitionUseCase
import com.example.combeertition.domain.competition.GetCompetitionByIdUseCase
import com.example.combeertition.domain.competition.GetCompetitionsUseCase
import com.example.combeertition.domain.model.CompetitionId
import kotlinx.coroutines.launch


class CompetitionsViewModel : ViewModel() {
    fun bindUI(context: Context): LiveData<List<CompetitionUI>> = liveData {
        val state =
            GetCompetitionsUseCase()().map { competition ->
                CompetitionUI(
                    id = competition.id,
                    name = competition.name,
                    color = competition.color,
                    mode = competition.mode
                )
            }.sortedBy { it.name }
        emit(state)
    }

    fun onDeleteCompetition(competitionId: CompetitionId) {
        viewModelScope.launch {
            GetCompetitionByIdUseCase()(competitionId)?.let { DeleteCompetitionUseCase()(it) }
        }
    }


}

