package com.example.combeertition.domain.rounds

import com.example.combeertition.domain.model.Round
import com.example.combeertition.domain.model.RoundId
import java.util.*
import kotlin.random.Random

class CreateRoundsUseCase {
    operator fun invoke(teams: List<String>, mode: String): List<String> {
        var list: List<Round> = emptyList()
        var rounds: List<Int> = listOf(0..teams.count()).flatten()
        for (i in 0..teams.count()) {
            for (j in i + 1..teams.count()) {
                val randomIndex = Random.nextInt(rounds.size)
                val round = Round.create(
                    RoundId(UUID.randomUUID().toString()),
                    randomIndex.toString(),
                    teams[i],
                    teams[j],
                    null,
                    null
                )
                rounds = rounds.filter { it != rounds[randomIndex] }
                list.plus(round)
            }
        }
        list.sortedBy { it.round.toInt() }
        return list.map { it -> it.id.value }
    }
}