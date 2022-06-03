package com.example.combeertition.domain.rounds

import android.content.ContentValues.TAG
import android.util.Log
import com.example.combeertition.data.roundsRepository
import com.example.combeertition.domain.model.Round
import com.example.combeertition.domain.model.RoundId
import java.lang.Math.sqrt
import java.util.*
import kotlin.random.Random

class CreateRoundsUseCase {
    operator fun invoke(teams: List<String>, mode: String): List<String> {
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
                        teams[i],
                        teams[j],
                        null,
                        null,
                        0,
                        0
                    )
                    countRounds = countRounds.filter { it != countRounds[randomIndex] }
                    list = list.plus(round)
                }
            }
            list = list.sortedBy { it.round.toInt() }
            roundsRepository.updateRounds(list)
        } else if (mode == "Knockout") {
            var final = 1
            while (final < teams.count())
                final *= 2
            val free = final - teams.count()
            val matches = listOf(1..kotlin.math.sqrt(final.toDouble()).toInt())
        }
        return list.map { it -> it.id.value }
    }
}