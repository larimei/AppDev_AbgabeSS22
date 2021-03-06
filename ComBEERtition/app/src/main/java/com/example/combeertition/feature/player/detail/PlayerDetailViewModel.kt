package com.example.combeertition.feature.player.detail

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.combeertition.domain.model.Player
import com.example.combeertition.domain.model.PlayerId
import com.example.combeertition.domain.player.*
import com.example.combeertition.feature.main.ui.navControllerGlobal
import com.example.combeertition.feature.player.PlayerUI
import kotlinx.coroutines.launch

class PlayerDetailViewModel : ViewModel() {

    fun bindUI(context: Context, playerId: PlayerId): LiveData<PlayerDetailUI?> = liveData {
        val player = GetPlayerByIdUseCase()(playerId)
        val state = player?.let {
            PlayerDetailUI(
                id = it.id,
                name = player.name,
                color = player.color,
                wins = player.wins,
                looses = player.looses,
                matches = player.matches
            )
        }
        emit(state)
    }

    fun onAddPlayer(playerId: PlayerId, name: String, icon: Int, color: Color) {
        viewModelScope.launch {
            AddPlayerUseCase()(Player.create(playerId, name, color, 0, 0, 0))
            navControllerGlobal?.popBackStack()
            navControllerGlobal?.navigate("player/" + playerId.value)
        }
    }

    fun onUpdatePlayer(playerId: PlayerId, name: String, color: Color, wins: Int, looses: Int, matches: Int) {
        viewModelScope.launch {
            UpdatePlayerUseCase()(playerId, name, color, wins, looses, matches)
            navControllerGlobal?.popBackStack()
            navControllerGlobal?.navigate("player/" + playerId.value)
        }
    }

    fun onDeletePlayer(playerId: PlayerId) {
        viewModelScope.launch {
            GetPlayerByIdUseCase()(playerId)?.let { DeletePlayerUseCase()(it) }
            navControllerGlobal?.popBackStack()
            navControllerGlobal?.navigate("players")
        }
    }

    fun checkForDelete(playerId: PlayerId): LiveData<Boolean> = liveData {
        val state = CheckForDeletePlayerUseCase()(playerId)
        emit(state)
    }
}