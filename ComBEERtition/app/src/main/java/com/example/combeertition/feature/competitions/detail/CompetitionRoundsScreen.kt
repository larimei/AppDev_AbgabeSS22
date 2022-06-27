package com.example.combeertition.feature.competitions.detail

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.combeertition.R
import com.example.combeertition.data.ExpandableCardModel
import com.example.combeertition.domain.model.CompetitionId
import com.example.combeertition.domain.model.RoundId
import com.example.combeertition.domain.model.Team
import com.example.combeertition.domain.model.TeamId
import com.example.combeertition.feature.components.ExpandableCard
import com.example.combeertition.feature.teams.detail.TeamDetailViewModel

@Composable
fun CompetitionRoundsScreen(
    competitionId: String,
    viewModel: CompetitionRoundsViewModel = viewModel(),
    viewModelTeam: TeamDetailViewModel = viewModel()
) {

    val rounds by viewModel.onCreateCards(CompetitionId(competitionId)).observeAsState(
        (emptyList())
    )
    val expandedCardIds = viewModel.expandedCardIdsList.collectAsState()
    CompetitionRoundsScreenUI(
        expandedCardIds,
        viewModel::onCardArrowClicked,
        viewModelTeam::bindUI,
        viewModel::onEditRound,
        rounds
    )
}


@Composable
fun CompetitionRoundsScreenUI(
    expandedCardIds: State<List<Int>>,
    onCardArrowClicked: (cardId: Int) -> Unit,
    onGetTeamById: (context: Context, teamId: TeamId) -> LiveData<Team?>,
    onEditRound: (roundId: RoundId, pointsFirst: Int, pointsSecond: Int) -> Unit,
    rounds: List<ExpandableCardModel>
) {
    if (rounds.isNotEmpty()) {
        Scaffold() {
            LazyColumn() {
                items(rounds) {round ->
                    ExpandableCard(
                        round = round,
                        onCardArrowClick = { onCardArrowClicked(round.id) },
                        expanded = expandedCardIds.value.contains(round.id),
                        onGetTeamById,
                        onEditRound
                    )
                }
            }
        }
    }
}
