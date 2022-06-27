package com.example.combeertition.domain.rounds

import android.content.ContentValues.TAG
import android.util.Log
import com.example.combeertition.data.roundsRepository
import com.example.combeertition.data.teamPlayerRepository
import com.example.combeertition.domain.model.CompetitionId
import com.example.combeertition.domain.model.Round
import com.example.combeertition.domain.model.RoundId
import java.lang.Math.sqrt
import java.util.*
import kotlin.math.log2
import kotlin.random.Random

class CreateRoundsUseCase {
    suspend operator fun invoke(
        teams: List<String>,
        mode: String,
        competitionId: CompetitionId
    ): List<String> {
        for (round in roundsRepository.getRoundByCompetitionId(competitionId)) {
            roundsRepository.deleteRound(round)
        }

        var list: List<Round> = emptyList()
        if (mode == "Jeder-gegen-Jeden") {
            var count = (teams.count() / 2.0) * (teams.count() - 1.0)
            var countRounds: List<Int> = listOf(0 until count.toInt()).flatten()
            for (i in 0..teams.count()) {
                for (j in i + 1 until teams.count()) {
                    val randomIndex = Random.nextInt(countRounds.size)
                    val round = Round.create(
                        RoundId(UUID.randomUUID().toString()),
                        (countRounds[randomIndex] + 1).toString(),
                        competitionId.value,
                        teams[i],
                        teams[j],
                        null,
                        null,
                        10,
                        10
                    )
                    roundsRepository.addRound(round)
                    countRounds = countRounds.filter { it != countRounds[randomIndex] }
                    list = list.plus(round)
                }
            }
            //list = list.sortedBy { it.round.toInt() }
        } else if (mode == "Knockout") {
            var teamsKnockout = teams
            var roundsPerRound = 1
            while (roundsPerRound < teams.count()/2.0)
                roundsPerRound *= 2
            var freePersons = roundsPerRound * 2 - teamsKnockout.count()
            var matches = log2(roundsPerRound.toDouble()) + 1
            var roundsWithoutPlayer = false


            for (i in 0 until freePersons) {
                val random = Random.nextInt(teamsKnockout.size)
                val round = Round.create(
                    RoundId(UUID.randomUUID().toString()),
                    roundsPerRound.toString(),
                    competitionId.value,
                    teamsKnockout[random],
                    "Freifahrt",
                    teamsKnockout[random],
                    null,
                    0,
                    10
                )
                teamsKnockout = teamsKnockout.filter { it != round.firstTeam }
                list = list.plus(round)
                roundsRepository.addRound(round)
            }

            while (matches >= 1.0) {
                var firstTeam = ""
                var secondTeam = ""
                for (i in 0 until roundsPerRound - freePersons) {
                    if (!roundsWithoutPlayer) {
                        var random = Random.nextInt(teamsKnockout.size)
                        firstTeam = teamsKnockout[random]
                        teamsKnockout = teamsKnockout.filter { it != firstTeam }
                        random = Random.nextInt(teamsKnockout.size)
                        secondTeam = teamsKnockout[random]
                        teamsKnockout = teamsKnockout.filter { it != secondTeam }
                    }
                    else {
                        firstTeam = "Gewinner 1"
                        secondTeam = "Gewinner 2"
                    }

                    val round = Round.create(
                        RoundId(UUID.randomUUID().toString()),
                        roundsPerRound.toString(),
                        competitionId.value,
                        firstTeam,
                        secondTeam,
                        null,
                        null,
                        10,
                        10
                    )
                    list = list.plus(round)
                    roundsRepository.addRound(round)
                    roundsWithoutPlayer = true
                }
                matches -= 1.0
                roundsPerRound /= 2
                freePersons = 0
            }
        }
        return list.map { it -> it.id.value }
    }
}