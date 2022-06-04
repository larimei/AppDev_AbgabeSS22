package com.example.combeertition.feature.teams

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.combeertition.domain.model.TeamId


@Composable
fun TeamsScreen(viewModel: TeamsViewModel = viewModel()) {
    val teams by viewModel.bindUI(LocalContext.current).observeAsState(
        (emptyList())
    )
    TeamsScreenUI(teams)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun TeamsScreenUI(teams: List<TeamUI>) {
    val scrollState = rememberLazyListState()
    LazyVerticalGrid(state = scrollState, cells = GridCells.Adaptive(minSize = 180.dp)) {
        items(teams) { team ->
            TeamItem(team)
        }
    }
}

