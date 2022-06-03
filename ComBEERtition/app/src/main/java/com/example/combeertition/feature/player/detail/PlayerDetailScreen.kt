package com.example.combeertition.feature.player.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.combeertition.domain.model.Player
import com.example.combeertition.R
import com.example.combeertition.data.playerRepository
import com.example.combeertition.domain.model.PlayerId
import com.github.skydoves.colorpicker.compose.ColorEnvelope
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import java.util.*

@Composable
fun PlayerDetailScreen(playerIdString: String, viewModel: PlayerDetailViewModel = viewModel()) {
    val player by viewModel.bindUI(LocalContext.current, PlayerId(playerIdString)).observeAsState()
    PlayerDetailScreenUI(playerIdString, player, viewModel::onAddPlayer, viewModel::onUpdatePlayer)
}


@Composable
fun PlayerDetailScreenUI(
    playerIdString: String?,
    player: Player?,
    onAddPlayer: (player: Player) -> Unit,
    onUpdatePlayer: (playerId: PlayerId, name: String, color: Color) -> Unit
) {
    if (playerIdString == "new" || player != null) {

        var playerId: PlayerId? = PlayerId(playerIdString ?: "new")
        if (playerId?.value == "new") {
            playerId = null
        }

        var name by remember { mutableStateOf(player?.name ?: "Spielername") }
        var color by remember { mutableStateOf(player?.color ?: Color.Black) }


        val controller = rememberColorPickerController()
        var set = false

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(player?.icon ?: R.drawable.ic_player),
                contentDescription = name,
                modifier = Modifier
                    .size(200.dp)
                    .padding(horizontal = 40.dp),
                tint = color
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
                    .padding(horizontal = 40.dp)
                    .fillMaxWidth()
            )
            HsvColorPicker(
                modifier = Modifier
                    .width(250.dp)
                    .height(250.dp)
                    .padding(40.dp),
                controller = controller,
                onColorChanged = { colorEnvelope: ColorEnvelope ->
                    if (set)
                        color = colorEnvelope.color
                    else set = true
                }
            )
            Button(
                onClick = {
                    if (playerId == null)
                        onAddPlayer(createPlayer(name, color))
                    else
                        onUpdatePlayer(playerId, name, color)
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
            ) {
                Row() {
                    Icon(
                        painterResource(R.drawable.ic_baseline_save_24),
                        contentDescription = "add player",
                        tint = Color.White,
                        modifier = Modifier.padding(horizontal = 4.dp)
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
}

fun createPlayer(name: String, color: Color): Player {
    return Player.create(PlayerId(UUID.randomUUID().toString()), name, R.drawable.ic_player, color)
}


