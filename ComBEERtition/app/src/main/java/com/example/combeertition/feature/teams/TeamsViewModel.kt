package com.example.combeertition.feature.teams

import android.content.Context
import androidx.lifecycle.*
import com.example.combeertition.domain.team.DeleteTeamUseCase
import com.example.combeertition.domain.team.GetTeamsUseCase
import com.example.combeertition.domain.model.TeamId
import com.example.combeertition.domain.team.GetTeamByIdUseCase
import kotlinx.coroutines.launch


class TeamsViewModel : ViewModel() {
    fun bindUI(context: Context): LiveData<List<TeamUI>> = liveData {
        val state =
            GetTeamsUseCase()().map { team ->
                TeamUI(
                    id = team.id,
                    name = team.name,
                    color = team.color
                )
            }.sortedBy { it.name }
        emit(state)
    }




}

