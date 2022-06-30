package com.example.combeertition.feature.player.detail

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.vector.Group
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.combeertition.domain.model.Player
import com.example.combeertition.R
import com.example.combeertition.data.playerRepository
import com.example.combeertition.domain.model.CompetitionId
import com.example.combeertition.domain.model.PlayerId
import com.example.combeertition.feature.components.ColorPicker
import com.example.combeertition.feature.components.DeleteOverlay
import com.example.combeertition.feature.main.ui.navControllerGlobal
import com.example.combeertition.ui.theme.RsBlue
import com.example.combeertition.ui.theme.RsRed
import com.example.combeertition.ui.theme.RsYellow
import com.github.skydoves.colorpicker.compose.ColorEnvelope
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import com.google.accompanist.pager.pagerTabIndicatorOffset
import java.util.*

@Composable
fun PlayerDetailScreen(playerIdString: String, viewModel: PlayerDetailViewModel = viewModel()) {
    val player by viewModel.bindUI(LocalContext.current, PlayerId(playerIdString)).observeAsState()
    val canDelete by viewModel.checkForDelete(PlayerId(playerIdString)).observeAsState()
    PlayerDetailScreenUI(
        playerIdString,
        player,
        viewModel::onAddPlayer,
        viewModel::onUpdatePlayer,
        viewModel::onDeletePlayer,
        canDelete
    )
}


@Composable
fun PlayerDetailScreenUI(
    playerIdString: String?,
    player: PlayerDetailUI?,
    onAddPlayer: (playerId: PlayerId, name: String, icon: Int, color: Color) -> Unit,
    onUpdatePlayer: (playerId: PlayerId, name: String, color: Color, wins: Int, looses: Int, matches: Int) -> Unit,
    onDeletePlayer: (playerId: PlayerId) -> Unit,
    canDelete: Boolean?
) {
    if (playerIdString == "new" || player != null) {

        var playerId: PlayerId? = PlayerId(playerIdString ?: "new")
        if (playerId?.value == "new") {
            playerId = null
        }

        var name by remember { mutableStateOf(player?.name ?: "Spielername") }
        var color = remember { mutableStateOf(player?.color ?: RsBlue) }
        var openDialog = remember { mutableStateOf(false) }
        var openDialogDelete = remember { mutableStateOf(false) }

        val scrollState = rememberScrollState()

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
                    Text(text = "Spieler", color = Color.White, fontSize = 20.sp)
                }
            }
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
                        .background(Color.White),
                ) {
                    IconButton(onClick = { openDialog.value = true }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_player),
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
                    .align(Alignment.BottomCenter),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .offset(y = 60.dp)
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
                                text = "Spielername",
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

                    Box {
                        Column(
                            modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp)
                        ) {
                            Text(
                                text = "Statistiken:",
                                fontSize = 20.sp,
                                color = MaterialTheme.colors.onSurface,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )
                            Spacer(modifier = Modifier.height(4.dp))

                            if (player?.matches != 0 && player != null) {
                                Stat(
                                    statName = "Gewonnen",
                                    statValue = player.wins,
                                    statMaxValue = player.matches,
                                    statColor = RsYellow,
                                    animDelay = 10
                                )
                                Stat(
                                    statName = "Verloren",
                                    statValue = player.looses,
                                    statMaxValue = player.matches,
                                    statColor = RsRed,
                                    animDelay = 10
                                )
                            } else {
                                Stat(
                                    statName = "Noch nichts gewonnen",
                                    statValue = 1,
                                    statMaxValue = 1,
                                    statColor = RsYellow,
                                    animDelay = 10,
                                    new = true
                                )

                                Stat(
                                    statName = "Noch nichts verloren",
                                    statValue = 1,
                                    statMaxValue = 1,
                                    statColor = RsRed,
                                    animDelay = 10,
                                    new = true
                                )
                            }
                        }
                    }
                    if (player != null) {
                        Button(
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = RsRed,
                                contentColor = Color.White
                            ),
                            onClick = {
                                if (canDelete == true) {
                                    onDeletePlayer(player.id)
                                } else {
                                    openDialogDelete.value = true
                                }
                            }, modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp)

                        ) {
                            Row() {
                                Icon(
                                    painterResource(R.drawable.ic_baseline_delete_24),
                                    contentDescription = "delete player",
                                    modifier = Modifier
                                        .padding(horizontal = 4.dp)
                                        .height(20.dp)
                                )
                                Text(
                                    text = ("Löschen"),
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                )
                            }
                        }
                    }
                    Button(
                        onClick = {
                            if (playerId == null)
                                onAddPlayer(
                                    PlayerId(UUID.randomUUID().toString()),
                                    name,
                                    R.drawable.ic_player,
                                    color.value,
                                )
                            else
                                if (player != null)
                                    onUpdatePlayer(
                                        playerId,
                                        name,
                                        color.value,
                                        player.wins,
                                        player.looses,
                                        player.matches
                                    )
                        }, modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                    ) {
                        Row() {
                            Icon(
                                painterResource(R.drawable.ic_baseline_save_24),
                                contentDescription = "add player",
                                tint = Color.White,
                                modifier = Modifier
                                    .padding(horizontal = 4.dp)
                                    .height(20.dp)
                            )
                            Text(
                                text = (if (playerId == null) "Hinzufügen" else "Speichern"),
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }
                    }
                }
            }
            if (openDialog.value) {
                ColorPicker(openDialog = openDialog, colorNew = color)
            }
            if (openDialogDelete.value) {
                DeleteOverlay(
                    openDialog = openDialogDelete,
                    "Ihr Spieler ist noch in mindestens einem Team und eventuell sogar in einem Turnier. Lösche das erst, bevor du den Spieler löschst."
                )
            }
        }
    }
}

@Composable
fun Stat(
    statName: String,
    statValue: Int,
    statMaxValue: Int,
    statColor: Color,
    height: Dp = 28.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0,
    new: Boolean = false
) {
    var animationPlayed by remember {
        mutableStateOf(false)
    }
    val curPercent = animateFloatAsState(
        targetValue = if (animationPlayed) {
            statValue / statMaxValue.toFloat()
        } else 0f,
        animationSpec = tween(
            animDuration,
            animDelay
        )
    )
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }
    Box(modifier = Modifier.padding(vertical = 8.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .clip(CircleShape)
                .background(
                    if (isSystemInDarkTheme()) {
                        Color(0xFF505050)
                    } else {
                        Color.LightGray
                    }
                )
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(curPercent.value)
                    .clip(CircleShape)
                    .background(statColor)
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = statName,
                    fontWeight = FontWeight.Bold
                )
                if (!new) {
                    Text(
                        text = (curPercent.value * statMaxValue).toInt().toString(),
                        fontWeight = FontWeight.Bold
                    )
                }
            }

        }
    }
}


