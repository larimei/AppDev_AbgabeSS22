package com.example.combeertition.feature.competitions.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.combeertition.feature.main.ui.navControllerGlobal
import com.example.combeertition.feature.teams.detail.AddTeamsOverlay
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch
import com.example.combeertition.R
import com.example.combeertition.domain.model.Competition
import com.example.combeertition.domain.model.CompetitionId
import com.example.combeertition.ui.theme.RsBlue
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CompetitionDetailScreen(
    competitionId: String,
    viewModel: CompetitionDetailViewModel = viewModel(),
) {
    val competition by viewModel.bindUI(LocalContext.current, CompetitionId(competitionId))
        .observeAsState()
    if (competition != null || competitionId == "new") {
        CompetitionDetailUIScreen(competitionIdString = competitionId, competition = competition)
    }

}

@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class)
@Composable
fun CompetitionDetailUIScreen(competitionIdString: String, competition: Competition?) {
    var tabIndex by remember { mutableStateOf(0) } // 1.
    val tabTitles = listOf("Info", "Runden")
    var color = remember { mutableStateOf(competition?.color ?: RsBlue) }

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
                Text(text = "Turnier", color = Color.White, fontSize = 20.sp)
            }
        }

        Column(modifier = Modifier.padding(top = 40.dp)) {
            TabRow(
                selectedTabIndex = tabIndex,
                backgroundColor = Color.Transparent,
                contentColor = Color.White
            ) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(selected = tabIndex == index, // 4.
                        onClick = { tabIndex = index },
                        text = { Text(text = title) }) // 5.
                }
            }
            when (tabIndex) { // 6.
                0 -> CompetitionInformationScreen(
                    competitionIdString = competitionIdString,
                    competition = competition,
                    color = color
                )
                1 -> CompetitionRoundsScreen(competitionId = competitionIdString)
            }
        }
    }
}

