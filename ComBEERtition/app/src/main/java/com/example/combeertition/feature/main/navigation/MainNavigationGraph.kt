package com.example.combeertition.feature.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.combeertition.feature.competitions.detail.CompetitionDetailScreen
import com.example.combeertition.feature.competitions.CompetitionsScreen
import com.example.combeertition.feature.player.PlayersScreen
import com.example.combeertition.feature.player.detail.PlayerDetailScreen
import com.example.combeertition.feature.teams.TeamsScreen
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
        composable("player/{playerId}") { backStackEntry ->
            backStackEntry.arguments?.getString("playerId")?.let { PlayerDetailScreen(it) }
        }
        composable("team/{teamId}") { backStackEntry ->
            backStackEntry.arguments?.getString("teamId")?.let { TeamDetailScreen(it) }
        }
        composable("competition/{competitionId}") { backStackEntry ->
            backStackEntry.arguments?.getString("competitionId")
                ?.let { CompetitionDetailScreen(it) }
        }
    }
}
