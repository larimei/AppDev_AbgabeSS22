package com.example.combeertition.domain.team

import androidx.compose.ui.graphics.Color
import com.example.combeertition.R
import com.example.combeertition.data.teamPlayerRepository
import com.example.combeertition.data.teamRepository
import com.example.combeertition.domain.model.*
import java.util.*
import kotlin.random.Random


class AddTeamsUseCase {
    val names = listOf(
        "Alcoballics",
        "Here For Beer!",
        "The Ponginators",
        "Dead Liver Society",
        "Alco HoliChiks",
        "Professional Drunks",
        "Beerkats",
        "Beer Brothers", "We’re Already Drunk", "Raging Alcoholics", "Beer It On",
        "Kim Pong-il",
        "Beer Barons",
        "The Walking Drunk",
        "Farfromsober",
        " Gettin’ Our Balls Wet",
        "Too Drunk To Dunk",
        " Mud, Sweat, And Beer",
        "Sharp Shooters",
        "Let Me See That Pong",
        "Here for Beer",
        "Pong Love",
        "Re-rack Attack",
        "Chilled Perfection",
        "Full of Stimulus",
        "Marvelous Mermaids",
        "Plastic Cups",
        "Gettin ’ Our Balls Wet",
        "The Hotshots",
        "Black and Brews",
        "Here for Beer!",
        "Drinking for Pleasure",
        "Waxed Balls",
        "Shake and Chug",
        "The Hotshots",
        " The Bus Drivers",
        "Incorrigible",
        "I’ ll Beer Right Back",
        " Everybody Drinks",
        "Collision Course",
        "Victorious Secret",
        "Shake and Bake", "",
        "Pink Thong",
        " Bahama Mamas",
        "Chickenwins",
        "Victorious Secret",
        "Balls Angels",
        "Hot Chicks Cold Beer",
        "Reife Frauen ab 50",
        "Girls from Ipanema",
        "Beach Bitches",
        "Die Turboschnecken",
        "Kamikatzen",
        "Spiced Rum Girls",
        "Starlight Exzess",
        "Mahatma Gönndir",
        "Die Steifen Hörnchen",
        "Desperados Houswives",
        "Ingorious Beerstars",
        "Teenage Mutant Ninja",
        "Pongers",
        " Benjamin Bierchen",
        "Masters of Pong Fu",
        "Cup & Cupper",
        "King Pong",
        "Beerlander",
        "The Walking Drunk",
        "Donkey Pong",
        "Pong Voyage",
        "Beer Berr Blocksberg",
        "KoBeer Bryant & LePong James",
        "Beerussia Dortmund",
        "Ajax Dauerstramm",
        "Alkohooligans",
        "Juventus Urin",
        "1899 besoffen sein",
        "FC Siewillja",
        "Hangover 96",
        "Arminia Bierzelt",
        "AS Rum",
        "Dynamo Tresen",
    )

    suspend operator fun invoke(count: Int, playersList: List<String>, random: Boolean): Boolean {
        var teams: List<Team> = listOf()
        var players: List<String> = playersList

        for (i in 1..count) {
            teams = teams.plus(
                Team.create(
                    TeamId(UUID.randomUUID().toString()),
                    names[Random.nextInt(names.size)],
                    R.drawable.ic_team,
                    Color.Blue,
                    emptyList(),
                    0,
                    0,
                    0
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

        for (team in teams) {
            teamRepository.addTeam(team)
            for (player in team.players) {
                teamPlayerRepository.addTeamPlayer(
                    TeamPlayer.create(
                        TeamPlayerId(
                            UUID.randomUUID().toString()
                        ),
                        team.id,
                        PlayerId(player)
                    )
                )
            }
        }
        return true
    }
}