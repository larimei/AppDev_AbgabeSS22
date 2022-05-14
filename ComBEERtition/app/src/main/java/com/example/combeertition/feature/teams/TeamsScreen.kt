package com.example.combeertition.feature.teams

import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.combeertition.domain.model.TeamId


@Composable
fun TeamsScreen(viewModel: TeamsViewModel = viewModel()) {
    val teams by viewModel.bindUI(LocalContext.current).observeAsState(
        (emptyList())
    )
    TeamsScreenUI(teams,viewModel::onDeleteTeam)
}

@Composable
private fun TeamsScreenUI(teams: List<TeamUI>, deleteTeam: (TeamId) -> Unit) {
    val scrollState = rememberLazyListState()
    LazyColumn(state = scrollState) {
        items(teams) { team ->
            TeamItem(team, deleteTeam)
        }
    }
}

@Preview
@Composable
fun TeamsScreen_Preview() {
    TeamsScreenUI(emptyList()) {}
}
