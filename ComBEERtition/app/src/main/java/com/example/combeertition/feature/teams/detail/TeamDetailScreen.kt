package com.example.combeertition.feature.teams.detail

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.combeertition.domain.model.Player
import com.example.combeertition.R
import com.example.combeertition.data.playerRepository
import com.example.combeertition.data.teamRepository
import com.example.combeertition.domain.model.PlayerId
import com.example.combeertition.domain.model.Team
import com.example.combeertition.domain.model.TeamId
import com.github.skydoves.colorpicker.compose.ColorEnvelope
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import java.util.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import com.example.combeertition.feature.player.detail.PlayerDetailViewModel

@Composable
fun TeamDetailScreen(
    teamIdString: String,
    viewModel: TeamDetailViewModel = viewModel(),
    viewModelPlayer: PlayerDetailViewModel = viewModel()
) {
    val team by viewModel.bindUI(LocalContext.current, TeamId(teamIdString)).observeAsState()
    TeamDetailScreenUI(viewModel::onUpdateTeam, viewModelPlayer::bindUI, team)
}


@Composable
fun TeamDetailScreenUI(
    onUpdateTeam: (teamId: TeamId, name: String, color: Color, players: List<String>) -> Unit,
    onGetPlayerById: (context: Context, playerId: PlayerId) -> LiveData<Player?>,
    team: Team?
) {
    if (team != null) {
        var name by remember { mutableStateOf(team.name) }
        var color by remember { mutableStateOf(team.color) }
        var players by remember { mutableStateOf(team.players) }

        val controller = rememberColorPickerController()
        var set = false

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(team.icon),
                contentDescription = name,
                modifier = Modifier
                    .size(200.dp)
                    .padding(horizontal = 40.dp),
                tint = color
            )
            OutlinedTextField(
                label = {
                    Text(
                        text = "Teamname/Motto",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
                value = name,
                onValueChange = { name = it },
                modifier = Modifier
                    .padding(horizontal = 40.dp)
                    .fillMaxWidth()
            )
            HsvColorPicker(
                modifier = Modifier
                    .width(250.dp)
                    .height(250.dp)
                    .padding(40.dp),
                controller = controller,
                onColorChanged = { colorEnvelope: ColorEnvelope ->
                    if (set)
                        color = colorEnvelope.color
                    else set = true
                }
            )
            //TODO schöner anordnen
            LazyColumn() {
                items(team.players) { player ->
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        val player by onGetPlayerById(
                            LocalContext.current,
                            PlayerId(player)
                        ).observeAsState()
                        Text(text = player?.name ?: "")
                    }
                }
            }
            Button(
                onClick = {
                    onUpdateTeam(team.id, name, color, players)
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
            ) {
                Row() {
                    Icon(
                        painterResource(R.drawable.ic_baseline_save_24),
                        contentDescription = "add team",
                        tint = Color.White,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                    Text(
                        text = "Speichern",
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }
}


