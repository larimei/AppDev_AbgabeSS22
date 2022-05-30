package com.example.combeertition.feature.components

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.combeertition.feature.competitions.detail.ExpandableCardModel
import com.example.combeertition.R
import com.example.combeertition.ui.theme.RsGrey
import com.example.combeertition.ui.theme.RsYellow
import org.intellij.lang.annotations.JdkConstants

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun ExpandableCard(
    round: ExpandableCardModel,
    onCardArrowClick: () -> Unit,
    expanded: Boolean,
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
                CardTitle(title = round.title)
            }
            ExpandableContent(visible = expanded, initialVisibility = expanded)
        }

    }
}


@Composable
fun CardTitle(title: String) {
    Text(
        text = title,
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
    initialVisibility: Boolean = false
) {
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
                        start = 28.dp,
                        top = 0.dp,
                        end = 28.dp,
                        bottom = 12.dp
                    )
            ) {
                Text(text = "Team 1")
                Text(text = "Team 2")
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        modifier = Modifier.background(RsYellow, CircleShape),
                        onClick = { },
                        content = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_baseline_exposure_neg_1_24),
                                contentDescription = "Expandable Arrow",
                                tint = Color.Black
                            )
                        }
                    )
                    Text(
                        text = "1", modifier = Modifier.padding(
                            horizontal = 14.dp
                        )
                    )
                    IconButton(
                        modifier = Modifier.background(RsYellow, CircleShape),
                        onClick = { },
                        content = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_baseline_exposure_plus_1_24),
                                contentDescription = "Expandable Arrow",
                                tint = Color.Black
                            )
                        }
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        modifier = Modifier.background(RsYellow, CircleShape),
                        onClick = { },
                        content = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_baseline_exposure_neg_1_24),
                                contentDescription = "Expandable Arrow",
                                tint = Color.Black
                            )
                        }
                    )
                    Text(
                        text = "5", modifier = Modifier.padding(
                            horizontal = 14.dp
                        )
                    )
                    IconButton(
                        modifier = Modifier.background(RsYellow, CircleShape),
                        onClick = { },
                        content = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_baseline_exposure_plus_1_24),
                                contentDescription = "Expandable Arrow",
                                tint = Color.Black
                            )
                        }
                    )
                }
            }
        }
    }
}