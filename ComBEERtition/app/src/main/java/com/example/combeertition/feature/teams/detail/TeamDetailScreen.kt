package com.example.combeertition.feature.teams.detail

import android.content.Context
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.LiveData
import com.example.combeertition.feature.competitions.detail.AddTeamsToCompetitionOverlay
import com.example.combeertition.feature.components.ColorPicker
import com.example.combeertition.feature.main.ui.navControllerGlobal
import com.example.combeertition.feature.player.detail.PlayerDetailViewModel
import com.example.combeertition.feature.player.detail.Stat
import com.example.combeertition.ui.theme.RsRed
import com.example.combeertition.ui.theme.RsYellow

@Composable
fun TeamDetailScreen(
    teamIdString: String,
    viewModel: TeamDetailViewModel = viewModel(),
    viewModelPlayer: PlayerDetailViewModel = viewModel()
) {
    val team by viewModel.bindUI(LocalContext.current, TeamId(teamIdString)).observeAsState()
    TeamDetailScreenUI(
        viewModel::onUpdateTeam,
        viewModelPlayer::bindUI,
        viewModel::onDeleteTeam,
        team
    )
}


@Composable
fun TeamDetailScreenUI(
    onUpdateTeam: (teamId: TeamId, name: String, color: Color, players: List<String>, wins: Int, looses: Int, matches: Int) -> Unit,
    onGetPlayerById: (context: Context, playerId: PlayerId) -> LiveData<Player?>,
    onDeleteTeam: (teamId: TeamId) -> Unit,
    team: Team?
) {
    if (team != null) {
        var name by remember { mutableStateOf(team.name) }
        var color = remember { mutableStateOf(team.color) }
        var players = remember { mutableStateOf(team.players) }
        var openDialog = remember { mutableStateOf(false) }
        var openDialogPlayer = remember { mutableStateOf(false) }

        val controller = rememberColorPickerController()
        var set = false

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            color.value,
                            Color.White
                        )
                    )
                )
        ) {
            Box(
                modifier = Modifier
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                Color.Black,
                                Color.Transparent
                            )
                        )
                    )
                    .fillMaxWidth()
                    .fillMaxHeight(0.2f)
                    .align(Alignment.TopCenter)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { navControllerGlobal?.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                    Text(text = "Team", color = Color.White, fontSize = 20.sp)
                }
            }
            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier
                    .zIndex(10.0F)
                    .fillMaxSize()
            )
            {
                Box(
                    modifier = Modifier
                        .size(190.dp)
                        .padding(40.dp)
                        .clip(RoundedCornerShape(50))
                        .background(Color.White),
                ) {
                    IconButton(onClick = { openDialog.value = true }) {
                        Icon(
                            painter = painterResource(team?.icon ?: R.drawable.ic_team),
                            contentDescription = name,
                            modifier = Modifier
                                .size(150.dp)
                                .padding(20.dp),
                            tint = color.value
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = 80.dp,
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 16.dp
                    )
                    .shadow(10.dp, RoundedCornerShape(10.dp))
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colors.surface)
                    .padding(16.dp)
                    .align(Alignment.BottomCenter),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .offset(y = 100.dp)
                ) {
                    Text(
                        text = name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.onSurface
                    )
                    OutlinedTextField(
                        label = {
                            Text(
                                text = "Teamname",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        },
                        value = name,
                        onValueChange = { name = it },
                        modifier = Modifier
                            .padding(horizontal = 40.dp, vertical = 20.dp)
                            .fillMaxWidth()
                    )

                    Box() {
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Statistiken:",
                                fontSize = 20.sp,
                                color = MaterialTheme.colors.onSurface,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                            Spacer(modifier = Modifier.height(4.dp))

                            if (team?.matches != 0) {
                                team?.let {
                                    Stat(
                                        statName = "Gewonnen",
                                        statValue = it.wins,
                                        statMaxValue = it.matches,
                                        statColor = RsYellow,
                                        animDelay = 10
                                    )
                                }
                                team?.let {
                                    Stat(
                                        statName = "Verloren",
                                        statValue = it.looses,
                                        statMaxValue = it.matches,
                                        statColor = RsRed,
                                        animDelay = 10
                                    )
                                }
                            } else {
                                team?.let {
                                    Stat(
                                        statName = "Noch nichts gewonnen",
                                        statValue = 1,
                                        statMaxValue = 1,
                                        statColor = RsYellow,
                                        animDelay = 10,
                                        new = true
                                    )
                                }
                                team?.let {
                                    Stat(
                                        statName = "Noch nichts verloren",
                                        statValue = 1,
                                        statMaxValue = 1,
                                        statColor = RsRed,
                                        animDelay = 10,
                                        new = true
                                    )
                                }
                            }
                        }
                    }
                    Box(modifier = Modifier.height(70.dp)) {
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
                    }
                    Button(
                        onClick = {
                            openDialogPlayer.value = true
                        }, modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                    ) {
                        Row() {
                            Icon(
                                painterResource(R.drawable.ic_player),
                                contentDescription = "add player",
                                tint = Color.White,
                                modifier = Modifier
                                    .padding(horizontal = 4.dp)
                                    .height(20.dp)
                            )
                            Text(
                                text = "Spieler hinzufügen",
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = RsRed,
                            contentColor = Color.White
                        ),
                        onClick = {
                            onDeleteTeam(team.id)
                        }, modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)

                    ) {
                        Row() {
                            Icon(
                                painterResource(R.drawable.ic_baseline_delete_24),
                                contentDescription = "delete team",
                                modifier = Modifier.padding(horizontal = 4.dp)
                            )
                            Text(
                                text = ("Löschen"),
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }
                    }

                    Button(
                        onClick = {
                            onUpdateTeam(
                                team.id,
                                name,
                                color.value,
                                players.value,
                                team.wins,
                                team.looses,
                                team.matches
                            )
                        }, modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
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
            if (openDialog.value) {
                ColorPicker(openDialog = openDialog, colorNew = color)
            }
            if (openDialogPlayer.value) {
                AddPlayerToTeamsOverlay(openDialog = openDialogPlayer, players)
            }
        }
    }
}


