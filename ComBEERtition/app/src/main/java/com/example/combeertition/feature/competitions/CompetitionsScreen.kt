package com.example.combeertition.feature.competitions

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun CompetitionsScreen(viewModel: CompetitionsViewModel = viewModel()) {
    val competitions by viewModel.bindUI(LocalContext.current).observeAsState(
        (emptyList())
    )
    CompetitionsScreenUI(competitions)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CompetitionsScreenUI(competitions: List<CompetitionUI>) {
    val scrollState = rememberLazyListState()
    LazyVerticalGrid(state = scrollState, cells = GridCells.Adaptive(minSize = 180.dp)) {
        items(competitions) { competition ->
            CompetitionItem(competition,)
        }
    }
}

