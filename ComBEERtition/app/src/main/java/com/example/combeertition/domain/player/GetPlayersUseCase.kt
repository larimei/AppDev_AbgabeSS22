package com.example.combeertition.domain.player

import com.example.combeertition.data.playerRepository

class GetPlayersUseCase {
    operator fun invoke() = playerRepository.getAllPlayers()
}