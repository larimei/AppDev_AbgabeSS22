package com.example.combeertition.feature.competitions.detail

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.combeertition.R
import com.example.combeertition.data.competitionRepository
import com.example.combeertition.data.playerRepository
import com.example.combeertition.data.teamRepository
import com.example.combeertition.domain.model.*
import com.example.combeertition.feature.components.ColorPicker
import com.example.combeertition.feature.main.ui.navControllerGlobal
import com.example.combeertition.feature.player.detail.Stat
import com.example.combeertition.feature.teams.detail.AddPlayerToTeamsOverlay
import com.example.combeertition.feature.teams.detail.AddTeamsOverlay
import com.example.combeertition.feature.teams.detail.TeamDetailViewModel
import com.example.combeertition.ui.theme.RsBlue
import com.example.combeertition.ui.theme.RsRed
import com.example.combeertition.ui.theme.RsYellow
import com.github.skydoves.colorpicker.compose.ColorEnvelope
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import java.util.*

@Composable
fun CompetitionInformationScreen(
    competitionIdString: String,
    viewModel: CompetitionDetailViewModel = viewModel(),
    viewModelTeam: TeamDetailViewModel = viewModel(),
    competition: Competition?,
    color: MutableState<Color>
) {

    CompetitionDetailScreenUI(
        competitionIdString,
        competition,
        viewModel::onAddCompetition,
        viewModel::onUpdateCompetition,
        viewModelTeam::bindUI,
        color
    )
}


@Composable
fun CompetitionDetailScreenUI(
    competitionIdString: String?,
    competition: Competition?,
    onAddCompetition: (competitionId: CompetitionId, name: String, color: Color, teams: List<String>, mode: String) -> Unit,
    onUpdateCompetition: (CompetitionId: CompetitionId, name: String, color: Color, teams: List<String>, mode: String) -> Unit,
    onGetTeamById: (context: Context, teamId: TeamId) -> LiveData<Team?>,
    color: MutableState<Color>
) {

    if (competition != null || competitionIdString == "new") {
        var competitionId: CompetitionId? = CompetitionId(competitionIdString ?: "new")
        if (competitionIdString == "new") {
            competitionId = null
        }
        val openDialog = remember { mutableStateOf(false) }
        val openDialogTeam = remember { mutableStateOf(false) }

        var name by remember { mutableStateOf(competition?.name ?: "Turniername") }

        var teams: MutableState<List<String>> =
            remember { mutableStateOf(competition?.teams ?: emptyList()) }
        var mode by remember { mutableStateOf(competition?.mode ?: "Knockout") }

        Box() {
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
                        .background(Color.White)

                ) {
                    IconButton(onClick = { openDialog.value = true }) {
                        Icon(
                            painter = painterResource(
                                competition?.icon ?: R.drawable.ic_competition
                            ),
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
                    .zIndex(9.0f)
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
                                text = "Turniername",
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
                    Row {
                        RadioButton(selected = mode == "Knockout", onClick = { mode = "Knockout" })
                        Text(
                            text = "Knockout",
                            modifier = Modifier
                                .clickable(onClick = { mode = "Knockout" })
                                .padding(start = 4.dp)
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        RadioButton(
                            selected = mode == "Jeder-gegen-Jeden",
                            onClick = { mode = "Jeder-gegen-Jeden" })
                        Text(
                            text = "Jeder-gegen-Jeden",
                            modifier = Modifier
                                .clickable(onClick = { mode = "Jeder-gegen-Jeden" })
                                .padding(start = 4.dp)
                        )
                    }
                    Box(modifier = Modifier.height(60.dp)) {
                        val scrollState = rememberLazyListState()
                        LazyColumn(state = scrollState, modifier = Modifier.height(100.dp)) {
                            items(teams.value) { team ->
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    val team by onGetTeamById(
                                        LocalContext.current,
                                        TeamId(team)
                                    ).observeAsState()
                                    Text(text = team?.name ?: "")
                                }
                            }
                        }
                    }
                    Button(
                        onClick = {
                            openDialogTeam.value = true
                        }, modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 40.dp)
                    ) {
                        Row() {
                            Icon(
                                painterResource(R.drawable.ic_team),
                                contentDescription = "add team",
                                tint = Color.White,
                                modifier = Modifier
                                    .padding(horizontal = 4.dp)
                                    .height(20.dp)
                            )
                            Text(
                                text = "Team hinzufügen",
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }
                    }
                    Button(
                        onClick = {
                            if (competitionId == null)
                                onAddCompetition(
                                    CompetitionId(UUID.randomUUID().toString()),
                                    name,
                                    color.value,
                                    teams.value,
                                    mode
                                )
                            else
                                if (competition != null) {
                                    onUpdateCompetition(
                                        competitionId,
                                        name,
                                        color.value,
                                        teams.value,
                                        mode
                                    )
                                }
                        }, modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 40.dp)
                    ) {
                        Row() {
                            Icon(
                                painterResource(R.drawable.ic_baseline_save_24),
                                contentDescription = "add Competition",
                                tint = Color.White,
                                modifier = Modifier.padding(horizontal = 4.dp)
                            )
                            Text(
                                text = (if (competitionId == null) "Hinzufügen" else "Speichern"),
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }
                    }
                }
                if (openDialog.value) {
                    ColorPicker(openDialog = openDialog, colorNew = color)
                }
                if (openDialogTeam.value) {
                    AddPlayerToTeamsOverlay(openDialog = openDialogTeam, teams)
                }
            }
        }
    }
}

