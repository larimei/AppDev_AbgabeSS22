package com.example.combeertition.domain.player

import androidx.compose.ui.graphics.Color
import com.example.combeertition.R
import com.example.combeertition.data.playerRepository
import com.example.combeertition.domain.model.Player
import com.example.combeertition.domain.model.PlayerId

class UpdatePlayerUseCase {
    operator fun invoke(playerId: PlayerId, name: String, color: Color): Boolean {
        val updatedPlayer = Player.create(playerId, name, R.drawable.ic_player, color)
        val allPlayers = playerRepository.getAllPlayers()
        val newPlayers = allPlayers.mapNotNull{if (it.id == playerId) updatedPlayer else it}
        playerRepository.updatePlayers(newPlayers)
        return true
    }
}