package com.example.combeertition.feature.competitions

import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.combeertition.domain.model.CompetitionId
import com.example.combeertition.feature.competition.CompetitionItem
import com.example.combeertition.feature.competition.CompetitionUI



@Composable
fun CompetitionsScreen(viewModel: CompetitionsViewModel = viewModel()) {
    val competitions by viewModel.bindUI(LocalContext.current).observeAsState(
        (emptyList())
    )
    CompetitionsScreenUI(competitions,viewModel::onDeleteCompetition)
}

@Composable
private fun CompetitionsScreenUI(competitions: List<CompetitionUI>, deleteCompetition: (CompetitionId) -> Unit) {
    val scrollState = rememberLazyListState()
    LazyColumn(state = scrollState) {
        items(competitions) { competition ->
            CompetitionItem(competition, deleteCompetition)
        }
    }
}

@Preview
@Composable
fun CompetitionsScreen_Preview() {
    CompetitionsScreenUI(emptyList()) {}
}
