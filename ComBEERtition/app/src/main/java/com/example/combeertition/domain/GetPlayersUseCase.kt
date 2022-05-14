package com.example.combeertition.domain

import com.example.combeertition.data.playerRepository

class GetPlayersUseCase {
    operator fun invoke() = playerRepository.getAllPlayers()
}