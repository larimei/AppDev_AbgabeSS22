package com.example.combeertition.domain.competition

import androidx.compose.ui.graphics.Color
import com.example.combeertition.R
import com.example.combeertition.data.competitionRepository
import com.example.combeertition.data.playerRepository
import com.example.combeertition.domain.model.Competition
import com.example.combeertition.domain.model.CompetitionId
import com.example.combeertition.domain.model.Player
import com.example.combeertition.domain.rounds.CreateRoundsUseCase
import kotlinx.coroutines.Job

class AddCompetitionUseCase(private val createRoundsUseCase: CreateRoundsUseCase) {
    operator fun invoke(
        competitionId: CompetitionId,
        name: String,
        color: Color,
        teams: List<String>,
        mode: String
    ): Boolean {
        val rounds = createRoundsUseCase(teams, mode)
        val competition = Competition.create(
            competitionId,
            name,
            R.drawable.ic_competition,
            teams,
            mode,
            rounds,
            color
        )
        val updatedCompetitions = competitionRepository.getAllCompetitions().plus(competition)
        competitionRepository.updateCompetitions(updatedCompetitions)
        return true
    }
}