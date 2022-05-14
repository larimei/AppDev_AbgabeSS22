package com.example.combeertition.feature.main.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.combeertition.feature.main.navigation.BottomNavigationItemApp
import com.example.combeertition.feature.main.navigation.MainBottomNavigation
import com.example.combeertition.feature.main.navigation.MainNavigationGraph
import com.example.combeertition.R

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryFlow
        .collectAsState(initial = navController.currentBackStackEntry)

    Scaffold(
        topBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            TopAppBar(
                title = {
                    when (currentRoute) {
                        BottomNavigationItemApp.Competitions.routeName -> Text(stringResource(BottomNavigationItemApp.Competitions.title))
                        BottomNavigationItemApp.Teams.routeName -> Text(stringResource(BottomNavigationItemApp.Teams.title))
                        BottomNavigationItemApp.Players.routeName -> Text(stringResource(BottomNavigationItemApp.Players.title))
                    }
                },
            )
        },
            floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            when (currentRoute.value?.destination?.route) {
                                //Overlay öffnen
                                "competitons" ->  {  }
                                "teams" -> { }
                                "players" -> { }
                            }
                        },
                        backgroundColor = Color(0xFFFFDE59),
                        content = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_baseline_add_24),
                                contentDescription = null,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    )
            },
        bottomBar = { MainBottomNavigation(navController) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            MainNavigationGraph(navController)
        }
    }
}

@Composable
@Preview
fun MainScreen_Preview() {
    MainScreen()
}
