package com.example.combeertition.domain.team

import androidx.compose.ui.graphics.Color
import com.example.combeertition.R
import com.example.combeertition.data.playerRepository
import com.example.combeertition.data.teamRepository
import com.example.combeertition.domain.model.Team
import com.example.combeertition.domain.model.TeamId
import java.util.*
import kotlin.random.Random

class AddTeamsUseCase {
    operator fun invoke(count: Int, playersList: List<String>, random: Boolean): Boolean {
        var teams: List<Team> = listOf()
        var players: List<String> = playersList
        println(playersList)

        for (i in 1..count) {
            teams = teams.plus(
                Team.create(
                    TeamId(UUID.randomUUID().toString()),
                    "Team$i",
                    R.drawable.ic_team,
                    Color.Blue,
                    emptyList()
                )
            )
        }


        while (players.isNotEmpty() && random) {
            for (i in 0 until count) {
                val randomIndex = Random.nextInt(players.size)
                teams[i].players = teams[i].players.plus(players[randomIndex])
                players = players.filter { it != players[randomIndex] }
                if (players.isEmpty()) break
            }
        }


        var updatedTeams = teamRepository.getAllTeams()
        for (team in teams) {
            updatedTeams = updatedTeams.plus(team)
        }
        teamRepository.updateTeams(updatedTeams)

        return true
    }
}