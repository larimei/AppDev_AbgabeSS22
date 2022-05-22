package com.example.combeertition.feature.competitions.detail.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.combeertition.feature.competitions.detail.CompetitionDetailScreen
import com.example.combeertition.feature.competitions.detail.CompetitionInformationScreen
import com.example.combeertition.feature.competitions.detail.CompetitionRoundsScreen

@Composable
fun CompetitionNavigationGraph(navController: NavHostController, competitionId: String) {
    NavHost(navController, startDestination = "info") {
        composable(BottomCompetitionNavigationItemApp.Information.routeName) {
            CompetitionInformationScreen(competitionId)
        }
        composable(BottomCompetitionNavigationItemApp.Rounds.routeName) {
            CompetitionRoundsScreen()
        }

    }
}
