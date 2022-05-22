package com.example.combeertition.data

import androidx.compose.ui.graphics.Color
import com.example.combeertition.R
import com.example.combeertition.domain.model.Competition
import com.example.combeertition.domain.model.CompetitionId
import com.example.combeertition.domain.model.Round
import com.example.combeertition.domain.model.RoundId

val roundsRepository = CompetitionRepository()

class RoundsRepository {
    private var allRounds = listOfNotNull(
        Round.create(
            id = RoundId("ac"),
            round = "Viertelfinale",
            firstTeam = "a",
            secondTeam = "b",
            winner = null,
            loser = null
        )
    )

    fun getAllRounds() = allRounds

    fun getRoundById(id: RoundId): Round? = allRounds.firstOrNull {
        it.id == id
    }

    fun updateRounds(newRounds: List<Round>): List<Round> {
        allRounds = newRounds
        return getAllRounds()
    }

}