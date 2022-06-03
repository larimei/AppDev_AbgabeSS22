package com.example.combeertition.feature.competitions.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.combeertition.feature.competitions.detail.navigation.BottomCompetitionNavigationItemApp
import com.example.combeertition.feature.competitions.detail.navigation.CompetitionBottomNavigation
import com.example.combeertition.feature.competitions.detail.navigation.CompetitionNavigationGraph
import com.example.combeertition.feature.main.navigation.BottomNavigationItemApp
import com.example.combeertition.feature.main.navigation.MainBottomNavigation
import com.example.combeertition.feature.main.navigation.MainNavigationGraph
import com.example.combeertition.feature.main.ui.navControllerGlobal
import com.example.combeertition.feature.teams.detail.AddTeamsOverlay

@Composable
fun CompetitionDetailScreen(competitionId: String) {
    val navController = rememberNavController()
    val currentRouteMain = navController.currentBackStackEntryFlow
        .collectAsState(initial = navController.currentBackStackEntry)

    Scaffold(
        topBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            TopAppBar(
                title = {
                    when (currentRoute) {
                        BottomCompetitionNavigationItemApp.Information.routeName -> Text(
                            stringResource(
                                BottomCompetitionNavigationItemApp.Information.title
                            )
                        )
                        BottomCompetitionNavigationItemApp.Rounds.routeName -> Text(
                            stringResource(
                                BottomCompetitionNavigationItemApp.Rounds.title
                            )
                        )
                    }
                },
                navigationIcon =
                {
                    IconButton(onClick = {
                        if (currentRouteMain.value?.destination?.route == "info") {
                            navControllerGlobal?.navigate("competitions")
                        } else {
                            println("jo")
                            navController?.navigateUp()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        bottomBar = {
            CompetitionBottomNavigation(navController)
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            CompetitionNavigationGraph(navController, competitionId)
        }
    }
}
