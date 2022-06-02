package com.example.combeertition.data

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.example.combeertition.R
import com.example.combeertition.domain.model.Competition
import com.example.combeertition.domain.model.CompetitionId
import com.example.combeertition.domain.model.Round
import com.example.combeertition.domain.model.RoundId


@Immutable
data class ExpandableCardModel(val id: Int, val round: Round?)

val roundsRepository = RoundsRepository()

class RoundsRepository {
    private var allRounds = listOfNotNull(
        Round.create(
            id = RoundId("ac"),
            round = "Viertelfinale",
            firstTeam = "a",
            secondTeam = "b",
            winner = null,
            loser = null,
            pointsFirst = 0,
            pointsSecond = 0
        )
    )

    fun getAllRounds() = allRounds

    fun getRoundById(id: RoundId): Round? = allRounds.firstOrNull {
        it.id == id
    }

    fun updateRounds(newRounds: List<Round>): List<Round> {
        for (round in newRounds) {
            if (allRounds.find { it.id == round.id } != null) {
                allRounds = allRounds.map { if (it.id == round.id) round else it }
            }
            else {
                allRounds = allRounds.plus(round)
            }
        }
        return getAllRounds()
    }

}