package com.example.combeertition.domain.rounds

import com.example.combeertition.data.RoundsRepository
import com.example.combeertition.data.roundsRepository
import com.example.combeertition.domain.model.CompetitionId
import com.example.combeertition.domain.model.Round
import com.example.combeertition.domain.model.RoundId

class SetWinnerToRoundUseCase {
    suspend operator fun invoke(
        competition: CompetitionId,
        round: RoundId
    ): Boolean {
        val rounds = roundsRepository.getRoundByCompetitionId(competition)
        for (round in rounds) {

        }

        return true

    }
}