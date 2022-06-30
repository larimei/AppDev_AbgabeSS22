package com.example.combeertition.domain.team

import androidx.compose.ui.graphics.Color
import com.example.combeertition.R
import com.example.combeertition.data.playerRepository
import com.example.combeertition.data.teamPlayerRepository
import com.example.combeertition.data.teamRepository
import com.example.combeertition.domain.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class UpdateTeamUseCase {
    suspend operator fun invoke(
        teamId: TeamId,
        name: String,
        color: Color,
        players: List<String>,
        wins: Int,
        looses: Int,
        matches: Int
    ) = withContext(Dispatchers.Default)
    {
        for (player in teamPlayerRepository.getByTeamId(teamId)) {
            teamPlayerRepository.deleteTeamPlayer(player)
        }
        for (player in players) {
            teamPlayerRepository.addTeamPlayer(
                TeamPlayer.create(
                    TeamPlayerId(
                        UUID.randomUUID().toString()
                    ),
                    teamId,
                    PlayerId(player)
                )
            )
        }
        teamRepository.updateTeam(
            Team.create(
                teamId,
                name,
                color,
                players,
                wins,
                looses,
                matches
            )
        )
    }

}