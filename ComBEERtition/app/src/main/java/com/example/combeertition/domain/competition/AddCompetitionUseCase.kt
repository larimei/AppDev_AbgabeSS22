package com.example.combeertition.domain.competition

import androidx.compose.ui.graphics.Color
import com.example.combeertition.R
import com.example.combeertition.data.competitionRepository
import com.example.combeertition.data.competitionTeamRepository
import com.example.combeertition.data.playerRepository
import com.example.combeertition.data.teamPlayerRepository
import com.example.combeertition.domain.model.*
import com.example.combeertition.domain.rounds.CreateRoundsUseCase
import kotlinx.coroutines.Job
import java.util.*

class AddCompetitionUseCase(private val createRoundsUseCase: CreateRoundsUseCase) {
    suspend operator fun invoke(
        name: String,
        color: Color,
        teams: List<String>,
        mode: String
    ): Boolean {
        val competitionId = CompetitionId(UUID.randomUUID().toString())
        val rounds = createRoundsUseCase(teams, mode, competitionId)

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
        competitionRepository.addCompetition(
            Competition.create(
                competitionId,
                name,
                R.drawable.ic_competition,
                teams,
                mode,
                rounds,
                color
            )
        )
        return true
    }
}