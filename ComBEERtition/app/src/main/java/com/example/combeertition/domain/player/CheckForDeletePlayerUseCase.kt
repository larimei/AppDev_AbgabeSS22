package com.example.combeertition.domain.player

import com.example.combeertition.data.PlayerRepository
import com.example.combeertition.data.playerRepository
import com.example.combeertition.data.teamPlayerRepository
import com.example.combeertition.data.teamRepository
import com.example.combeertition.domain.model.Player
import com.example.combeertition.domain.model.PlayerId
import com.example.combeertition.domain.model.TeamPlayer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CheckForDeletePlayerUseCase() {
    suspend operator fun invoke(playerId: PlayerId): Boolean {
        for (team in teamPlayerRepository.getByPlayerId(playerId)) {
            return false
        }
        return true
    }
}