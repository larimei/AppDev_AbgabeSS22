package com.example.combeertition.feature.teams.detail


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.combeertition.R
import com.example.combeertition.feature.player.PlayerUI
import com.example.combeertition.feature.player.PlayersViewModel
import com.example.combeertition.feature.teams.TeamUI
import com.example.combeertition.feature.teams.TeamsViewModel


@Composable
fun AddPlayerToTeamsOverlay(
    openDialog: MutableState<Boolean>,
    players: MutableState<List<String>>,
    viewModel: PlayersViewModel = viewModel(),
) {
    val playersList by viewModel.bindUI(LocalContext.current).observeAsState(
        (emptyList())
    )
    AddPlayersToTeamOverlayUi(openDialog, players,playersList)
}

@Composable
fun AddPlayersToTeamOverlayUi(
    openDialog: MutableState<Boolean>,
    players: MutableState<List<String>>,
    playersList: List<PlayerUI>
) {
    val playersOverlay: MutableState<List<String>> = remember { mutableStateOf(players.value) }

    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onCloseRequest.
            openDialog.value = false
        },
        title = {
            Text(text = "Spieler hinzufügen")
        },
        text = {
            Box(
                modifier = Modifier
                    .wrapContentSize(align = Alignment.TopStart)
                    .height(400.dp)
            ) {
                LazyColumn() {
                    items(playersList) { player ->
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = player.name)
                            IconButton(onClick = {
                                if (playersOverlay.value.find { it == player.id.value } == null)
                                    playersOverlay.value = playersOverlay.value.plus(player.id.value)
                                else
                                    playersOverlay.value =
                                        playersOverlay.value.filter { it != player.id.value }
                            }) {
                                Icon(
                                    painterResource(
                                        if (playersOverlay.value.find { it == player.id.value } == null)
                                            R.drawable.ic_baseline_add_24
                                        else
                                            R.drawable.ic_baseline_delete_24),
                                    contentDescription = "add team",
                                    tint = Color.Black,
                                    modifier = Modifier.padding(horizontal = 4.dp)
                                )
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    players.value = playersOverlay.value
                    openDialog.value = false
                }) {
                Text("Hinzufügen")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    openDialog.value = false
                }) {
                Text("Abbrechen")
            }
        }
    )
}