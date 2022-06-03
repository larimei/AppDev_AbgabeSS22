package com.example.combeertition.feature.player.detail

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.combeertition.domain.player.AddPlayerUseCase
import com.example.combeertition.domain.player.UpdatePlayerUseCase
import com.example.combeertition.domain.model.Player
import com.example.combeertition.domain.model.PlayerId
import com.example.combeertition.domain.player.GetPlayerByIdUseCase
import com.example.combeertition.domain.player.GetPlayersUseCase
import com.example.combeertition.feature.main.ui.navControllerGlobal
import com.example.combeertition.feature.player.PlayerUI
import kotlinx.coroutines.launch

class PlayerDetailViewModel : ViewModel() {

    fun bindUI(context: Context, playerId: PlayerId): LiveData<Player?> = liveData {
        val state = GetPlayerByIdUseCase()(playerId)
        emit(state)
    }

    fun onAddPlayer(player: Player) {
        viewModelScope.launch {
            AddPlayerUseCase()(player)
            navControllerGlobal?.navigate("players")
        }
    }

    fun onUpdatePlayer(playerId: PlayerId, name: String, color: Color) {
        viewModelScope.launch {
            println("update")
            UpdatePlayerUseCase()(playerId, name, color)
        }
    }
}