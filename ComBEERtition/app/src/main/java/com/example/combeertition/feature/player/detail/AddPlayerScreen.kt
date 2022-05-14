package com.example.combeertition.feature.player.detail


import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.combeertition.R
import com.github.skydoves.colorpicker.compose.ColorEnvelope
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController


@Composable
fun AddPlayerScreen() {
    var name by remember { mutableStateOf("Spieler") }
    var color by remember { mutableStateOf(Color.Black) }

    val controller = rememberColorPickerController()
    var set = false

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            painter = painterResource(R.drawable.ic_player),
            contentDescription = name,
            modifier = Modifier
                .size(200.dp)
                .padding(horizontal = 40.dp),
            tint = color
        )
        TextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier.padding(horizontal = 40.dp).fillMaxWidth()
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
    }
}