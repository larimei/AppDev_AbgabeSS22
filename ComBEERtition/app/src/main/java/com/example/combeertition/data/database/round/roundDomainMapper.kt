package com.example.combeertition.data.database.round

import com.example.combeertition.domain.model.Round
import com.example.combeertition.domain.model.RoundId

fun roundToDb(round: Round): RoundDb = RoundDb(
    roundId = round.id.value,
    round = round.round,
    firstTeam = round.firstTeam,
    secondTeam = round.secondTeam,
    winner = round.winner,
    loser = round.loser,
    pointsFirst = round.pointsFirst,
    pointsSecond = round.pointsSecond,
    created = round.created,
    updated = round.updated,
    deleted = round.deleted,
)

fun roundFromDb(roundDb: RoundDb): Round? {
    return Round.create(
        id = RoundId(roundDb.roundId),
        round = roundDb.round,
        firstTeam = roundDb.firstTeam,
        secondTeam = roundDb.secondTeam,
        winner = roundDb.winner,
        loser = roundDb.loser,
        pointsFirst = roundDb.pointsFirst,
        pointsSecond = roundDb.pointsSecond,
    )
}