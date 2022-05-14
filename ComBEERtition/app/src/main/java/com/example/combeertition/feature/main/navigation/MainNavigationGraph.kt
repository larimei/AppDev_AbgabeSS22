package com.example.combeertition.feature.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.combeertition.feature.competitions.CompetitionsScreen
import com.example.combeertition.feature.competitions.detail.CompetitionDetailScreen
import com.example.combeertition.feature.player.PlayersScreen
import com.example.combeertition.feature.player.detail.AddPlayerScreen
import com.example.combeertition.feature.player.detail.PlayerDetailScreen
import com.example.combeertition.feature.teams.TeamsScreen
import com.example.combeertition.feature.teams.detail.AddTeamScreen
import com.example.combeertition.feature.teams.detail.TeamDetailScreen

@Composable
fun MainNavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "competitions") {
        composable(BottomNavigationItemApp.Competitions.routeName) {
            CompetitionsScreen()
        }
        composable(BottomNavigationItemApp.Teams.routeName) {
            TeamsScreen()
        }
        composable(BottomNavigationItemApp.Players.routeName) {
            PlayersScreen()
        }
        composable("addPlayer") {
            AddPlayerScreen()
        }
        composable("playerDetail") {
            //PlayerDetailScreen()
        }
        composable("addTeam") {
            AddTeamScreen()
        }
        composable("teamDetail") {
            TeamDetailScreen()
        }
        composable("competitionDetail") {
            CompetitionDetailScreen()
        }
    }
}
