package com.example.combeertition.feature.player

import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.combeertition.domain.model.PlayerId


@Composable
fun PlayersScreen(viewModel: PlayersViewModel = viewModel()) {
    val players by viewModel.bindUI(LocalContext.current).observeAsState(
        (emptyList())
    )
    PlayersScreenUI(players,viewModel::onDeletePlayer)
}

@Composable
private fun PlayersScreenUI(players: List<PlayerUI>, deletePlayer: (PlayerId) -> Unit) {
    val scrollState = rememberLazyListState()
    LazyColumn(state = scrollState) {
        items(players) { player ->
            PlayerItem(player, deletePlayer)
        }
    }
}

@Preview
@Composable
fun PlayersScreen_Preview() {
    PlayersScreenUI(emptyList()) {}
}
