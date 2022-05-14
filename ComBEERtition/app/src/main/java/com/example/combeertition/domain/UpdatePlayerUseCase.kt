package com.example.combeertition.domain

import androidx.compose.ui.graphics.Color
import com.example.combeertition.data.playerRepository
import com.example.combeertition.domain.model.Player
import com.example.combeertition.domain.model.PlayerId

class UpdatePlayerUseCase {
    operator fun invoke(playerId: PlayerId, name: String, color: Color): Boolean {
        val updatedPlayer = playerRepository.getPlayerById(playerId)
        //fertig machen
        return true
    }
}