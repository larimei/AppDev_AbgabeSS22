package com.example.combeertition.feature.player

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.combeertition.domain.model.PlayerId


@Composable
fun PlayersScreen(viewModel: PlayersViewModel = viewModel()) {
    val players by viewModel.bindUI(LocalContext.current).observeAsState(
        (emptyList())
    )
    PlayersScreenUI(players)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlayersScreenUI(players: List<PlayerUI>) {
    val scrollState = rememberLazyListState()
    LazyVerticalGrid(state = scrollState, cells = GridCells.Adaptive(minSize = 180.dp)) {
        items(players) { player ->
            PlayerItem(player)
        }
    }
}
