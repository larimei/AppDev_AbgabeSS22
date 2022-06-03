package com.example.combeertition.domain.player

import com.example.combeertition.data.playerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetPlayersUseCase {
    suspend operator fun invoke() =
        withContext(Dispatchers.Default) { playerRepository.getAllPlayers() }
}