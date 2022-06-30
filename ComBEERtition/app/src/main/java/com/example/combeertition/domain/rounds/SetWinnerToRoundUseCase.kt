package com.example.combeertition.domain.rounds

import com.example.combeertition.data.RoundsRepository
import com.example.combeertition.data.competitionRepository
import com.example.combeertition.data.roundsRepository
import com.example.combeertition.domain.model.CompetitionId
import com.example.combeertition.domain.model.Round
import com.example.combeertition.domain.model.RoundId
import kotlin.math.log2

class SetWinnerToRoundUseCase {
    suspend operator fun invoke(
        teams: List<String>,
        competitionId: CompetitionId
    ): List<Round> {
        val rounds = roundsRepository.getRoundByCompetitionId(competitionId)
        var list: List<Round> = emptyList()

        var roundsPerRound = 1
        while (roundsPerRound < teams.count() / 2.0)
            roundsPerRound *= 2

        var indexWinner = 0

        println(teams?.count())
        println(roundsPerRound)
        for ((index, round) in rounds.sortedByDescending { it.round.toInt() }.withIndex()) {

            if (round.firstTeam == "" || round.secondTeam == "") {
                list = list.plus(
                    Round.create(
                        round.id,
                        round.round,
                        round.competition,
                        rounds[indexWinner].winner ?: "",
                        rounds[indexWinner + 1].winner ?: "",
                        round.winner,
                        round.loser,
                        round.pointsFirst,
                        round.pointsSecond
                    )
                )
            } else {
                list = list.plus(round)
            }
            if (index >= roundsPerRound) {
                indexWinner += 2
            }
        }
        return list
    }
}