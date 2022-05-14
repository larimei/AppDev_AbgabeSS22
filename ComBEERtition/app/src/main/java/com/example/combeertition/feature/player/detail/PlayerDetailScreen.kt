package com.example.combeertition.feature.player.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.combeertition.domain.model.Player
import com.example.combeertition.R
import com.example.combeertition.data.PlayerRepository
import com.example.combeertition.data.playerRepository
import com.example.combeertition.domain.AddPlayerUseCase
import com.example.combeertition.domain.UpdatePlayerUseCase
import com.example.combeertition.domain.model.PlayerId
import com.example.combeertition.feature.main.ui.navControllerGlobal
import com.github.skydoves.colorpicker.compose.ColorEnvelope
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController


@Composable
fun PlayerDetailScreen(navController: NavHostController, playerIdString: String?) {
    var playerId: PlayerId? = PlayerId(playerIdString ?: "new")
    if (playerIdString == "new") {
        playerId = null
    }
    val player = playerId?.let { playerRepository.getPlayerById(it) }

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
        TextField(
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
        Button(onClick = {
            if (playerId == null)
                onAddPlayer(createPlayer(name, color))
            else
                onUpdatePlayer(playerId, name, color)
        }) {
            Row() {
                Icon(
                    painterResource(R.drawable.ic_baseline_delete_24),
                    contentDescription = "add player",
                    tint = Color.White
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


fun createPlayer(name: String, color: Color): Player {
    return Player.create(PlayerId("sadg"), name, R.drawable.ic_player, color) //TODO unique id
}

fun onAddPlayer(player: Player) {
    AddPlayerUseCase()(player)
    navControllerGlobal?.navigate("player/" + player.id.value)
}

fun onUpdatePlayer(playerId: PlayerId, name: String, color: Color) {
    UpdatePlayerUseCase()(playerId, name, color) //TODO usecase noch implementieren
    navControllerGlobal?.navigate("player/" + playerId.value)
}
