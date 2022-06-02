package com.example.combeertition.feature.teams

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.combeertition.domain.team.DeleteTeamUseCase
import com.example.combeertition.domain.team.GetTeamsUseCase
import com.example.combeertition.domain.model.TeamId


class TeamsViewModel : ViewModel() {
    fun bindUI(context: Context): LiveData<List<TeamUI>> {
        val state = MutableLiveData(
            GetTeamsUseCase()().map { team ->
                TeamUI(
                    id = team.id,
                    name = team.name,
                    icon = team.icon,
                    color = team.color
                )
            }.sortedBy { it.name }
        )
        return state
    }

    fun onDeleteTeam(teamId: TeamId) {
        DeleteTeamUseCase()(teamId)
    }


}

