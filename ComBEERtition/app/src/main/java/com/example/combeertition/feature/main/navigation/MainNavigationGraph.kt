package com.example.combeertition.feature.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.combeertition.feature.competitions.CompetitionsScreen
import com.example.combeertition.feature.player.PlayersScreen
import com.example.combeertition.feature.teams.TeamsScreen

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
    }
}
