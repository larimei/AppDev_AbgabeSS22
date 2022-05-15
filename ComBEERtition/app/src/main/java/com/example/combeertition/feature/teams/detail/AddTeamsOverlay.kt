package com.example.combeertition.feature.teams.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.combeertition.R
import com.example.combeertition.data.playerRepository
import com.example.combeertition.feature.player.detail.createPlayer

@Composable
fun AddTeamsOverlay(
    openDialog: MutableState<Boolean>,
    viewModel: TeamDetailViewModel = viewModel()
) {
    AddTeamsOverlayUi(openDialog, viewModel::onAddTeams)
}

@Composable
fun AddTeamsOverlayUi(
    openDialog: MutableState<Boolean>,
    onAddTeams: (count: Int, players: List<String>, random: Boolean) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15).map { it.toString() }
    var selectedIndex by remember { mutableStateOf(0) }
    val checkedState = remember { mutableStateOf(false) }
    val players: MutableState<List<String>> = remember { mutableStateOf(emptyList()) }

    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onCloseRequest.
            openDialog.value = false
        },
        title = {
            Text(text = "Teams hinzufügen")
        },
        text = {
            Box(
                modifier = Modifier
                    .wrapContentSize(align = Alignment.TopStart)
                    .height(500.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    OutlinedTextField(
                        label = {
                            Text(
                                text = "Anzahl Teams auswählen",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        },
                        value = items[selectedIndex],
                        onValueChange = { },
                        enabled = false,
                        modifier = Modifier
                            .padding(horizontal = 40.dp)
                            .fillMaxWidth()
                            .clickable(onClick = { expanded = true })
                    )
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier
                            .width(350.dp)
                            .background(
                                Color.White
                            )
                            .height(400.dp)
                            .shadow((-10).dp)
                            .padding(20.dp)
                    ) {
                        items.forEachIndexed { index, s ->
                            DropdownMenuItem(onClick = {
                                selectedIndex = index
                                expanded = false
                            }) {
                                Text(text = s)
                            }
                        }

                    }
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Spieler zufällig hinzufügen")
                        Checkbox(
                            checked = checkedState.value,
                            onCheckedChange = { checkedState.value = it }
                        )
                    }
                    if (checkedState.value) {
                        Box(modifier = Modifier.height(300.dp)) {
                            LazyColumn() {
                                items(playerRepository.getAllPlayers()) { player ->
                                    Row(
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text(text = player.name)
                                        IconButton(onClick = {
                                            if (players.value.find { it == player.id.value } == null)
                                                players.value = players.value.plus(player.id.value)
                                            else
                                                players.value =
                                                    players.value.filter { it != player.id.value }
                                        }) {
                                            Icon(
                                                painterResource(
                                                    if (players.value.find { it == player.id.value } == null)
                                                        R.drawable.ic_baseline_add_24
                                                    else
                                                        R.drawable.ic_baseline_delete_24),
                                                contentDescription = "add player",
                                                tint = Color.Black,
                                                modifier = Modifier.padding(horizontal = 4.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    onAddTeams(items[selectedIndex].toInt(), players.value, checkedState.value)
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