package com.example.combeertition.feature.main.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.combeertition.feature.main.navigation.BottomNavigationItemApp
import com.example.combeertition.feature.main.navigation.MainBottomNavigation
import com.example.combeertition.feature.main.navigation.MainNavigationGraph
import com.example.combeertition.R
import com.example.combeertition.feature.teams.detail.AddTeamsOverlay
import com.example.combeertition.ui.theme.RsGrey

var navControllerGlobal: NavHostController? = null

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val currentRouteMain = navController.currentBackStackEntryFlow
        .collectAsState(initial = navController.currentBackStackEntry)
    val openDialog = remember { mutableStateOf(false) }

    navControllerGlobal = navController

    Scaffold(

        topBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            if (when (currentRouteMain.value?.destination?.route) {
                    "competitions", "teams", "players" -> true
                    else -> false
                }
            )
                TopAppBar(
                    title = {
                        when (currentRoute) {
                            BottomNavigationItemApp.Competitions.routeName -> Text(
                                stringResource(
                                    BottomNavigationItemApp.Competitions.title
                                )
                            )
                            BottomNavigationItemApp.Teams.routeName -> Text(
                                stringResource(
                                    BottomNavigationItemApp.Teams.title
                                )
                            )
                            BottomNavigationItemApp.Players.routeName -> Text(
                                stringResource(
                                    BottomNavigationItemApp.Players.title
                                )
                            )
                        }
                    },
                    navigationIcon =
                    {
                        if (currentRouteMain.value?.destination?.route != "competitions") {
                            IconButton(onClick = { navController.navigateUp() }) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        }
                    },
                    backgroundColor = RsGrey,
                    contentColor = Color.Black
                )
        },
        floatingActionButton = {
            if (when (currentRouteMain.value?.destination?.route) {
                    "competitions", "teams", "players" -> true
                    else -> false
                }
            ) {
                FloatingActionButton(
                    onClick = {
                        when (currentRouteMain.value?.destination?.route) {
                            "competitions" -> {
                                navController.navigate("competition/new")
                            }
                            "teams" -> {
                                openDialog.value = true
                            }
                            "players" -> {
                                navController.navigate("player/new")
                            }
                        }
                    },
                    content = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_add_24),
                            contentDescription = null,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                )
            }
        },
        bottomBar = {
            if (when (currentRouteMain.value?.destination?.route) {
                    "competitions", "teams", "players" -> true
                    else -> false
                }
            ) MainBottomNavigation(navController)
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            MainNavigationGraph(navController)
        }
    }
    if (openDialog.value) {
        AddTeamsOverlay(openDialog = openDialog)
    }
}

@Composable
@Preview
fun MainScreen_Preview() {
    MainScreen()
}
