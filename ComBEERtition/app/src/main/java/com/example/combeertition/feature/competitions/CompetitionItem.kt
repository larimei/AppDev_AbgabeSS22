package com.example.combeertition.feature.competitions

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.combeertition.R
import com.example.combeertition.feature.main.ui.navControllerGlobal


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CompetitionItem(competition: CompetitionUI) {
    Box(modifier = Modifier.padding(20.dp)) {
        Box(modifier = Modifier
            .size(130.dp)
            .shadow(10.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .background(
                Brush.verticalGradient(
                    listOf(
                        competition.color,
                        Color.White
                    )
                )
            )
            .clickable {
                navControllerGlobal?.navigate(
                    "competition/" + competition.id.value
                )
            }
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth().fillMaxHeight()
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_competition),
                    contentDescription = competition.name,
                    modifier = Modifier
                        .size(70.dp)
                        .padding(8.dp),
                    tint = Color.Black
                )

                Text(
                    text = competition.name,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = competition.mode,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
