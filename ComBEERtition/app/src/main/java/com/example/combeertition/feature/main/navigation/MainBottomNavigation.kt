package com.example.combeertition.feature.main.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun MainBottomNavigation(navController: NavController) {
    BottomNavigation(modifier = Modifier.height(64.dp)) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        listOf(
            BottomNavigationItemApp.Competitions,
            BottomNavigationItemApp.Teams,
            BottomNavigationItemApp.Players
        ).forEach { navItem ->
            BottomNavigationItem(
                selected = currentRoute == navItem.routeName,
                icon = {
                        Icon(painter = painterResource(navItem.icon),
                            modifier = Modifier.size(44.dp),
                            contentDescription = navItem.routeName)
                },
                onClick = {
                    navController.navigate(navItem.routeName) {
                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }

}
