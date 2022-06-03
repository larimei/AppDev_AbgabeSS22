package com.example.combeertition.feature.competitions.detail

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.combeertition.R
import com.example.combeertition.data.competitionRepository
import com.example.combeertition.data.playerRepository
import com.example.combeertition.data.teamRepository
import com.example.combeertition.domain.model.*
import com.example.combeertition.feature.teams.detail.AddTeamsOverlay
import com.example.combeertition.feature.teams.detail.TeamDetailViewModel
import com.github.skydoves.colorpicker.compose.ColorEnvelope
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import java.util.*

@Composable
fun CompetitionInformationScreen(
    competitionIdString: String,
    viewModel: CompetitionDetailViewModel = viewModel(),
    viewModelTeam: TeamDetailViewModel = viewModel()
) {
    CompetitionDetailScreenUI(
        competitionIdString,
        viewModel::onAddCompetition,
        viewModel::onUpdateCompetition,
        viewModelTeam::bindUI
    )
}


@Composable
fun CompetitionDetailScreenUI(
    competitionIdString: String?,
    onAddCompetition: (competitionId: CompetitionId, name: String, color: Color, teams: List<String>, mode: String) -> Unit,
    onUpdateCompetition: (CompetitionId: CompetitionId, name: String, color: Color, teams: List<String>, mode: String) -> Unit,
    onGetTeamById: (context: Context, teamId: TeamId) -> LiveData<Team?>
) {
    var competitionId: CompetitionId? = CompetitionId(competitionIdString ?: "new")
    if (competitionIdString == "new") {
        competitionId = null
    }
    val competition = competitionId?.let { competitionRepository.getCompetitionById(it) }
    val openDialog = remember { mutableStateOf(false) }

    var name by remember { mutableStateOf(competition?.name ?: "Turniername") }
    var color by remember { mutableStateOf(competition?.color ?: Color.Black) }
    var teams: MutableState<List<String>> =
        remember { mutableStateOf(competition?.teams ?: emptyList()) }
    var mode by remember { mutableStateOf(competition?.mode ?: "Knockout") }

    val controller = rememberColorPickerController()
    var set = false

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            painter = painterResource(competition?.icon ?: R.drawable.ic_competition),
            contentDescription = name,
            modifier = Modifier
                .size(125.dp)
                .padding(horizontal = 20.dp),
            tint = color
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
                .padding(horizontal = 40.dp)
                .fillMaxWidth()
        )
        HsvColorPicker(
            modifier = Modifier
                .width(180.dp)
                .height(180.dp)
                .padding(10.dp),
            controller = controller,
            onColorChanged = { colorEnvelope: ColorEnvelope ->
                if (set)
                    color = colorEnvelope.color
                else set = true
            }
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
        //TODO schöner anordnen
        val scrollState = rememberLazyListState()
        LazyColumn(state = scrollState, modifier = Modifier.height(100.dp)) {
            items(teams.value) { team ->
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val team by onGetTeamById(LocalContext.current, TeamId(team)).observeAsState()
                    Text(text = team?.name ?: "")
                }
            }
        }
        Button(
            onClick = {
                openDialog.value = true
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
        ) {
            Row() {
                Icon(
                    painterResource(R.drawable.ic_team),
                    contentDescription = "add Team",
                    tint = Color.White,
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .height(30.dp)
                )
                Text(
                    text = "Teams hinzufügen",
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
                        color,
                        teams.value,
                        mode
                    )
                else
                    if (competition != null) {
                        onUpdateCompetition(
                            competitionId,
                            name,
                            color,
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
        AddTeamsToCompetitionOverlay(openDialog = openDialog, teams)
    }
}



