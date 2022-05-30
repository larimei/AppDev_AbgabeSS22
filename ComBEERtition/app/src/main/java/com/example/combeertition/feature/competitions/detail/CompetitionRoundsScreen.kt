package com.example.combeertition.feature.competitions.detail

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable;
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.combeertition.R
import com.example.combeertition.feature.components.ExpandableCard

@Composable
fun CompetitionRoundsScreen(viewModel: CompetitionRoundsViewModel = viewModel()) {
    val rounds = viewModel.rounds.collectAsState()
    val expandedCardIds = viewModel.expandedCardIdsList.collectAsState()
    Scaffold {
        LazyColumn {
            itemsIndexed(rounds.value) { _, round ->
                ExpandableCard(
                    round = round,
                    onCardArrowClick = { viewModel.onCardArrowClicked(round.id) },
                    expanded = expandedCardIds.value.contains(round.id),
                )
            }
        }
    }

}
