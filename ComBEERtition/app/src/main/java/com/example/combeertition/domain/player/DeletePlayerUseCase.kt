package com.example.combeertition.domain.player

import com.example.combeertition.data.playerRepository
import com.example.combeertition.domain.model.Player
import com.example.combeertition.domain.model.PlayerId

class DeletePlayerUseCase {

    suspend operator fun invoke(player: Player): Boolean {
        playerRepository.deletePlayer(player)
        return true
    }
}
