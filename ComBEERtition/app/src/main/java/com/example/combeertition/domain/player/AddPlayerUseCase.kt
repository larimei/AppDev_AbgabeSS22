package com.example.combeertition.domain.player

import com.example.combeertition.data.PlayerRepository
import com.example.combeertition.data.playerRepository
import com.example.combeertition.domain.model.Player
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddPlayerUseCase() {
    suspend operator fun invoke(player: Player) = withContext(Dispatchers.Default) {
        playerRepository.addPlayer(player)
    }


}