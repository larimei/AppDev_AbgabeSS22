package com.example.combeertition.data

import androidx.compose.runtime.Immutable
import com.example.combeertition.App
import com.example.combeertition.data.database.round.RoundDao
import com.example.combeertition.data.database.round.roundFromDb
import com.example.combeertition.data.database.round.roundToDb
import com.example.combeertition.domain.model.Round
import com.example.combeertition.domain.model.RoundId


@Immutable
data class ExpandableCardModel(val id: Int, val round: Round?)

val roundsRepository = RoundsRepository(App.database.roundDao())

class RoundsRepository(private val dao: RoundDao) {
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

    suspend fun getRoundById(id: RoundId): Round? =
        dao.getById(id.value)?.let { roundFromDb(it) }

    suspend fun addRound(round: Round) {
        dao.insert(roundToDb(round))
    }

    suspend fun deleteRound(round: Round) {
        dao.delete(roundToDb(round))
    }

    suspend fun updateRound(round: Round) {
        dao.update(roundToDb(round))
    }

}