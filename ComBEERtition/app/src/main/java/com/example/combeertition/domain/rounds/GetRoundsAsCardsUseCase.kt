package com.example.combeertition.domain.rounds

import com.example.combeertition.data.ExpandableCardModel
import com.example.combeertition.data.competitionRepository
import com.example.combeertition.data.playerRepository
import com.example.combeertition.data.roundsRepository
import com.example.combeertition.domain.model.CompetitionId
import com.example.combeertition.domain.model.Player
import com.example.combeertition.domain.model.RoundId

class GetRoundsAsCardsUseCase() {
    operator fun invoke(competitionId: CompetitionId): List<ExpandableCardModel> {
        val comp = competitionRepository.getCompetitionById(competitionId)
        var cards: List<ExpandableCardModel> = emptyList()
        if (comp != null) {
            cards = comp.rounds.mapIndexed { index, round ->
                ExpandableCardModel(
                    id = index,
                    round = roundsRepository.getRoundById(RoundId(round))
                )
            }
        }
        return cards
    }
}