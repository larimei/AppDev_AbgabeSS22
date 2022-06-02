package com.example.combeertition.feature.competitions

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.combeertition.domain.competition.DeleteCompetitionUseCase
import com.example.combeertition.domain.competition.GetCompetitionsUseCase
import com.example.combeertition.domain.model.CompetitionId
import com.example.combeertition.feature.competition.CompetitionUI


class CompetitionsViewModel : ViewModel() {
    fun bindUI(context: Context): LiveData<List<CompetitionUI>> {
        val state = MutableLiveData(
            GetCompetitionsUseCase()().map { competition ->
                CompetitionUI(
                    id = competition.id,
                    name = competition.name,
                    icon = competition.icon,
                    color = competition.color,
                    mode = competition.mode
                )
            }.sortedBy { it.name }
        )
        return state
    }

    fun onDeleteCompetition(competitionId: CompetitionId) {
        DeleteCompetitionUseCase()(competitionId)
    }


}

