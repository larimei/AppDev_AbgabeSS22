package com.example.combeertition.domain.player

import com.example.combeertition.data.playerRepository
import com.example.combeertition.data.teamRepository
import com.example.combeertition.domain.model.Player
import com.example.combeertition.domain.model.PlayerId
import com.example.combeertition.domain.model.Team
import com.example.combeertition.domain.model.TeamId

class GetPlayerByIdUseCase {
    suspend operator fun invoke(playerId: PlayerId): Player? {
        return playerRepository.getPlayerById(playerId)
    }
}