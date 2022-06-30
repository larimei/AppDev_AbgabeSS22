package com.example.combeertition.feature.competitions.detail

import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.combeertition.R
import com.example.combeertition.feature.teams.TeamUI
import com.example.combeertition.feature.teams.TeamsViewModel


@Composable
fun AddTeamsToCompetitionOverlay(
    openDialog: MutableState<Boolean>,
    teams: MutableState<List<String>>,
    viewModel: TeamsViewModel = viewModel(),
) {
    val teamsList by viewModel.bindUI(LocalContext.current).observeAsState(
        (emptyList())
    )
    AddTeamsToCompetitionOverlayUi(openDialog, teams,teamsList)
}

@Composable
fun AddTeamsToCompetitionOverlayUi(
    openDialog: MutableState<Boolean>,
    teams: MutableState<List<String>>,
    teamsList: List<TeamUI>
) {
    val teamsOverlay: MutableState<List<String>> = remember { mutableStateOf(teams.value) }

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
                    .height(400.dp)
            ) {
                LazyColumn() {
                    items(teamsList) { team ->
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = team.name)
                            IconButton(onClick = {
                                if (teamsOverlay.value.find { it == team.id.value } == null)
                                    teamsOverlay.value = teamsOverlay.value.plus(team.id.value)
                                else
                                    teamsOverlay.value =
                                        teamsOverlay.value.filter { it != team.id.value }
                            }) {
                                Icon(
                                    painterResource(
                                        if (teamsOverlay.value.find { it == team.id.value } == null)
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
                    teams.value = teamsOverlay.value
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