package com.example.combeertition.feature.player.detail

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.combeertition.domain.AddPlayerUseCase
import com.example.combeertition.domain.UpdatePlayerUseCase
import com.example.combeertition.domain.model.Player
import com.example.combeertition.domain.model.PlayerId
import com.example.combeertition.feature.main.ui.navControllerGlobal

class PlayerDetailViewModel: ViewModel() {

    fun onAddPlayer(player: Player) {
        AddPlayerUseCase()(player)
        navControllerGlobal?.navigate("player/" + player.id.value)
    }

    fun onUpdatePlayer(playerId: PlayerId, name: String, color: Color) {
        UpdatePlayerUseCase()(playerId, name, color) //TODO usecase noch implementieren
    }
}