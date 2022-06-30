package com.example.combeertition.feature.components

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import com.example.combeertition.R
import com.example.combeertition.data.ExpandableCardModel
import com.example.combeertition.domain.model.Round
import com.example.combeertition.domain.model.RoundId
import com.example.combeertition.domain.model.Team
import com.example.combeertition.domain.model.TeamId
import com.example.combeertition.ui.theme.RsGrey
import com.example.combeertition.ui.theme.RsYellow
import org.intellij.lang.annotations.JdkConstants

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun ExpandableCard(
    round: ExpandableCardModel,
    onCardArrowClick: () -> Unit,
    expanded: Boolean,
    onGetTeamById: (context: Context, teamId: TeamId) -> LiveData<Team?>,
    onEditRound: (roundId: RoundId, pointsFirst: Int, pointsSecond: Int) -> Unit,
    mode: String
) {

    val transitionState = remember {
        MutableTransitionState(expanded).apply {
            targetState = !expanded
        }
    }
    val transition = updateTransition(transitionState, label = "")
    val cardBgColor by transition.animateColor({
        tween(durationMillis = 300)
    }, label = "") {
        if (expanded) Color.White else Color.White
    }
    val cardPaddingHorizontal by transition.animateDp({
        tween(durationMillis = 300)
    }, label = "") {
        if (expanded) 48.dp else 24.dp
    }
    val cardElevation by transition.animateDp({
        tween(durationMillis = 300)
    }, label = "") {
        if (expanded) 24.dp else 4.dp
    }
    val cardRoundedCorners by transition.animateDp({
        tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        )
    }, label = "") {
        if (expanded) 0.dp else 16.dp
    }
    val arrowRotationDegree by transition.animateFloat({
        tween(durationMillis = 300)
    }, label = "") {
        if (expanded) 0f else 180f
    }

    Card(
        backgroundColor = cardBgColor,
        elevation = cardElevation,
        shape = RoundedCornerShape(cardRoundedCorners),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = cardPaddingHorizontal,
                vertical = 8.dp
            )
    ) {

        Column {
            Box {
                CardArrow(
                    degrees = arrowRotationDegree,
                    onClick = onCardArrowClick
                )
                round.round?.round?.let { CardTitle(title = it, mode = mode) }
            }
            round.round?.let {
                ExpandableContent(
                    visible = expanded,
                    initialVisibility = expanded,
                    round = it,
                    onGetTeamById,
                    onEditRound
                )
            }
        }

    }
}


@Composable
fun CardTitle(title: String, mode: String) {
    Text(
        text = if (mode=="Jeder-gegen-Jeden") "Runde $title" else getText(title.toInt()),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        textAlign = TextAlign.Center,
    )
}

@Composable
fun CardArrow(
    degrees: Float,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        content = {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_expand_less_24),
                contentDescription = "Expandable Arrow",
                modifier = Modifier.rotate(degrees),
                tint = Color.Black
            )
        }
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ExpandableContent(
    visible: Boolean = true,
    initialVisibility: Boolean = false,
    round: Round,
    onGetTeamById: (context: Context, teamId: TeamId) -> LiveData<Team?>,
    onEditRound: (roundId: RoundId, pointsFirst: Int, pointsSecond: Int) -> Unit
) {

    val firstTeam by onGetTeamById(LocalContext.current, TeamId(round.firstTeam)).observeAsState()
    val secondTeam by onGetTeamById(LocalContext.current, TeamId(round.secondTeam)).observeAsState()
    var pointsFirst by remember { mutableStateOf(round.pointsFirst) }
    var pointsSecond by remember { mutableStateOf(round.pointsSecond) }

    val enterFadeIn = remember {
        fadeIn(
            animationSpec = TweenSpec(
                durationMillis = 300,
                easing = FastOutLinearInEasing
            )
        )
    }
    val enterExpand = remember {
        expandVertically(animationSpec = tween(300))
    }
    val exitFadeOut = remember {
        fadeOut(
            animationSpec = TweenSpec(
                durationMillis = 300,
                easing = LinearOutSlowInEasing
            )
        )
    }
    val exitCollapse = remember {
        shrinkVertically(animationSpec = tween(300))
    }
    AnimatedVisibility(
        visible = visible,
        initiallyVisible = initialVisibility,
        enter = enterExpand + enterFadeIn,
        exit = exitCollapse + exitFadeOut
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        bottom = 12.dp
                    )
            ) {
                firstTeam?.name?.let { Text(text = it) }
                secondTeam?.name?.let { Text(text = it) }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (pointsFirst != 0 && pointsSecond != 0) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(
                            modifier = Modifier.background(RsYellow, CircleShape),
                            onClick = {
                                if (pointsFirst > 0) {
                                    pointsFirst--
                                    onEditRound(round.id, pointsFirst, pointsSecond)
                                }
                            },
                            content = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_baseline_exposure_neg_1_24),
                                    contentDescription = "Expandable Arrow",
                                    tint = Color.Black
                                )
                            }
                        )
                        Text(
                            text = pointsFirst.toString(), modifier = Modifier.padding(
                                horizontal = 14.dp
                            )
                        )
                        IconButton(
                            modifier = Modifier.background(RsYellow, CircleShape),
                            onClick = {
                                if (pointsFirst < 10) {
                                    pointsFirst++
                                    onEditRound(round.id, pointsFirst, pointsSecond)
                                }
                            },
                            content = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_baseline_add_24),
                                    contentDescription = "Expandable Arrow",
                                    tint = Color.Black
                                )
                            }
                        )
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(
                            modifier = Modifier.background(RsYellow, CircleShape),
                            onClick = {
                                if (pointsSecond > 0) {
                                    pointsSecond--
                                    onEditRound(round.id, pointsFirst, pointsSecond)
                                }
                            },
                            content = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_baseline_exposure_neg_1_24),
                                    contentDescription = "Expandable Arrow",
                                    tint = Color.Black
                                )
                            }
                        )
                        Text(
                            text = pointsSecond.toString(), modifier = Modifier.padding(
                                horizontal = 14.dp
                            )
                        )
                        IconButton(
                            modifier = Modifier.background(RsYellow, CircleShape),
                            onClick = {
                                if (pointsSecond < 10) {
                                    pointsSecond++
                                    onEditRound(round.id, pointsFirst, pointsSecond)
                                }
                            },
                            content = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_baseline_add_24),
                                    contentDescription = "Expandable Arrow",
                                    tint = Color.Black
                                )
                            }
                        )
                    }
                } else if (pointsFirst == 0) {
                    Text("Verlierer")
                    Text("Gewinner")
                } else if (pointsSecond == 0) {
                    Text("Gewinner")
                    Text("Verlierer")
                }
            }
        }
    }
}

fun getText(number: Int): String {
    return when (number) {
        1 -> "Finale"
        2 -> "Halbfinale"
        4 -> "Viertelfinale"
        8 -> "Achtelfinale"
        16 -> "Sechszehntelfinale"
        32 -> "Zweiunddreissigstelfinale"
        else -> {
            "1/$number-Finale"
        }
    }

}