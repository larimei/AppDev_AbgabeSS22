package com.example.combeertition.feature.competitions.detail.navigation

import com.example.combeertition.R

sealed class BottomCompetitionNavigationItemApp {
    abstract val routeName: String
    abstract val title: Int
    abstract val icon: Int

    object Information : BottomCompetitionNavigationItemApp() {
        override val routeName = "info"
        override val title = R.string.info_title_navigation
        override val icon = R.drawable.ic_baseline_info_24
    }

    object Rounds : BottomCompetitionNavigationItemApp() {
        override val title = R.string.rounds_title_navigation
        override val routeName = "rounds"
        override val icon = R.drawable.ic_tournament
    }
}
