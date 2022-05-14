package com.example.combeertition.feature.player

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.combeertition.domain.DeletePlayerUseCase
import com.example.combeertition.domain.GetPlayersUseCase
import com.example.combeertition.domain.model.PlayerId


class PlayersViewModel : ViewModel() {
    fun bindUI(context: Context): LiveData<List<PlayerUI>> {
        val state = MutableLiveData(
            GetPlayersUseCase()().map { player ->
                PlayerUI(
                    id = player.id,
                    name = player.name,
                    icon = player.icon
                )
            }.sortedBy { it.name }
        )
        return state
    }

    fun onDeletePlayer(playerId: PlayerId) {
        DeletePlayerUseCase()(playerId)
    }


}

