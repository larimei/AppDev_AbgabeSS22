package com.example.combeertition.domain.competition

import androidx.compose.ui.graphics.Color
import com.example.combeertition.R
import com.example.combeertition.data.competitionRepository
import com.example.combeertition.data.competitionTeamRepository
import com.example.combeertition.data.teamPlayerRepository
import com.example.combeertition.domain.model.*
import com.example.combeertition.domain.rounds.CreateRoundsUseCase
import java.util.*

class UpdateCompetitionUseCase(private val createRoundsUseCase: CreateRoundsUseCase) {
    suspend operator fun invoke(
        competitionId: CompetitionId,
        name: String,
        color: Color,
        teams: List<String>,
        mode: String
    ): Boolean {
        val rounds = createRoundsUseCase(teams, mode, competitionId)

        for (team in competitionTeamRepository.getByCompetitionId(competitionId)) {
            competitionTeamRepository.deleteCompetitionTeam(team)
        }

        for (team in teams) {
            competitionTeamRepository.addCompetitionTeam(
                CompetitionTeam.create(
                    CompetitionTeamId(
                        UUID.randomUUID().toString()
                    ),
                    competitionId,
                    TeamId(team)
                )
            )
        }
        competitionRepository.updateCompetition(Competition.create(
            competitionId,
            name,
            teams,
            mode,
            rounds,
            color
        ))
        return true
    }
}