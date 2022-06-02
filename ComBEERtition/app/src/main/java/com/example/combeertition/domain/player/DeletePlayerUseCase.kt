package com.example.combeertition.domain.player

import com.example.combeertition.data.playerRepository
import com.example.combeertition.domain.model.PlayerId

class DeletePlayerUseCase {

    operator fun invoke(playerId: PlayerId): Boolean {
        val player = playerRepository.getPlayerById(playerId) ?: return false

        val updatedPlayers = playerRepository.getAllPlayers().filter{it != player}
        playerRepository.updatePlayers(updatedPlayers)
        return true
    }
}
