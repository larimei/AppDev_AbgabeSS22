package com.example.combeertition.feature.player

import android.content.Context
import androidx.lifecycle.*
import com.example.combeertition.domain.model.Player
import com.example.combeertition.domain.player.DeletePlayerUseCase
import com.example.combeertition.domain.player.GetPlayersUseCase
import com.example.combeertition.domain.model.PlayerId
import com.example.combeertition.domain.player.GetPlayerByIdUseCase
import kotlinx.coroutines.launch


class PlayersViewModel : ViewModel() {
    fun bindUI(context: Context): LiveData<List<PlayerUI>> = liveData {
        val state = GetPlayersUseCase()().map { player ->
            PlayerUI(
                id = player.id,
                name = player.name,
                icon = player.icon,
                color = player.color
            )
        }.sortedBy { it.name }
        emit(state)
    }

}

