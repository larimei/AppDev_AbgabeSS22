package com.example.combeertition.domain.player

import com.example.combeertition.data.playerRepository
import com.example.combeertition.domain.model.Player

class AddPlayerUseCase {
    operator fun invoke(player: Player): Boolean {
        val updatedPlayers = playerRepository.getAllPlayers().plus(player)
        playerRepository.updatePlayers(updatedPlayers)
        return true
    }
}