package com.example.combeertition.domain.rounds

import androidx.compose.ui.graphics.Color
import com.example.combeertition.R
import com.example.combeertition.data.roundsRepository
import com.example.combeertition.data.teamRepository
import com.example.combeertition.domain.model.Round
import com.example.combeertition.domain.model.RoundId
import com.example.combeertition.domain.model.Team
import com.example.combeertition.domain.model.TeamId

class EditRoundUseCase {
    operator fun invoke(roundId: RoundId, pointsFirst: Int, pointsSecond: Int): Boolean {
        val updatedRound = roundsRepository.getRoundById(roundId)
        var newRound: List<Round> = emptyList()

        if (updatedRound != null) {
            newRound = newRound.plus(
                Round.create(
                    roundId,
                    updatedRound.round,
                    updatedRound.firstTeam,
                    updatedRound.secondTeam,
                    updatedRound.winner,
                    updatedRound.loser,
                    pointsFirst,
                    pointsSecond
                )
            )
            roundsRepository.updateRounds(newRound)
            return true
        }
        return false
    }
}