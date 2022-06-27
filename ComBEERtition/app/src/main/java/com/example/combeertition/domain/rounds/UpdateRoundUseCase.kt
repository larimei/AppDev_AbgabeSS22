package com.example.combeertition.domain.rounds

import androidx.compose.ui.graphics.Color
import com.example.combeertition.R
import com.example.combeertition.data.roundsRepository
import com.example.combeertition.data.teamRepository
import com.example.combeertition.domain.model.Round
import com.example.combeertition.domain.model.RoundId
import com.example.combeertition.domain.model.Team
import com.example.combeertition.domain.model.TeamId

class UpdateRoundUseCase {
    suspend operator fun invoke(roundId: RoundId, pointsFirst: Int, pointsSecond: Int): Boolean {
        val updatedRound = roundsRepository.getRoundById(roundId)

        if (updatedRound != null) {
            if (pointsFirst == 0) {
                roundsRepository.updateRound(
                    Round.create(
                        roundId,
                        updatedRound.round,
                        updatedRound.competition,
                        updatedRound.firstTeam,
                        updatedRound.secondTeam,
                        updatedRound.secondTeam,
                        updatedRound.firstTeam,
                        pointsFirst,
                        pointsSecond
                    )
                )
            } else if (pointsSecond == 0) {
                roundsRepository.updateRound(
                    Round.create(
                        roundId,
                        updatedRound.round,
                        updatedRound.competition,
                        updatedRound.firstTeam,
                        updatedRound.secondTeam,
                        updatedRound.firstTeam,
                        updatedRound.secondTeam,
                        pointsFirst,
                        pointsSecond
                    )
                )

            } else {
                roundsRepository.updateRound(
                    Round.create(
                        roundId,
                        updatedRound.round,
                        updatedRound.competition,
                        updatedRound.firstTeam,
                        updatedRound.secondTeam,
                        updatedRound.winner,
                        updatedRound.loser,
                        pointsFirst,
                        pointsSecond
                    )
                )
            }
            return true
        }
        return false
    }
}