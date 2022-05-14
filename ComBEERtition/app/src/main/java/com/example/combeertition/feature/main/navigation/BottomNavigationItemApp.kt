package com.example.combeertition.feature.main.navigation

import com.example.combeertition.R

sealed class BottomNavigationItemApp {
    abstract val routeName: String
    abstract val title: Int
    abstract val icon: Int

    object Players : BottomNavigationItemApp() {
        override val routeName = "players"
        override val title = R.string.players_title_navigation
        override val icon = R.drawable.ic_player
    }

    object Teams : BottomNavigationItemApp() {
        override val title = R.string.teams_title_navigation
        override val routeName = "teams"
        override val icon = R.drawable.ic_team
    }

    object Competitions : BottomNavigationItemApp() {
        override val title = R.string.competitions_title_navigation
        override val routeName = "competitions"
        override val icon = R.drawable.ic_competition
    }
}
