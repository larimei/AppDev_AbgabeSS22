package com.example.combeertition.domain.player

import androidx.compose.ui.graphics.Color
import com.example.combeertition.R
import com.example.combeertition.data.playerRepository
import com.example.combeertition.domain.model.Player
import com.example.combeertition.domain.model.PlayerId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UpdatePlayerUseCase {
    suspend operator fun invoke(playerId: PlayerId, name: String, color: Color, wins: Int, looses: Int, matches: Int) = withContext(
        Dispatchers.Default
    ) {
        playerRepository.updatePlayer(Player.create(playerId, name, color, wins, looses, matches))
    }
}