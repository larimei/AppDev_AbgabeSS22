package com.example.combeertition.feature.competitions

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.combeertition.R
import com.example.combeertition.domain.model.CompetitionId
import com.example.combeertition.feature.competition.CompetitionUI
import com.example.combeertition.feature.main.ui.navControllerGlobal
import com.example.combeertition.ui.theme.RsRed


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CompetitionItem(competition: CompetitionUI, deleteCompetition: (id: CompetitionId) -> Unit) {
    Card(
        elevation = 3.dp,
        modifier = Modifier.padding(8.dp),
        onClick = { navControllerGlobal?.navigate("competition/" + competition.id.value)}
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(competition.icon),
                contentDescription = competition.name,
                modifier = Modifier
                    .size(50.dp)
                    .padding(end = 8.dp),
                tint = competition.color
            )
            Column (){
                 Text(
                    text = competition.name,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = competition.mode,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Box(
                modifier = Modifier.align(Alignment.CenterVertically),
            ) {
                IconButton(
                    onClick = { deleteCompetition(competition.id) },
                ) {
                    Icon(painterResource(R.drawable.ic_baseline_delete_24),
                        contentDescription = "delete competition",
                        tint = RsRed)
                }
            }
        }
    }
}

@Preview
@Composable
fun CompetitionItem_Preview() {
    CompetitionItem(
        CompetitionUI(
            CompetitionId("foo"),
            "Lara",
            R.drawable.ic_competition,
            Color.Green,
            "Knockout"
        )
    ) {}
}
