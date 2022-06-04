package com.example.combeertition.feature.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.combeertition.R
import com.example.combeertition.feature.player.PlayerUI
import com.example.combeertition.ui.theme.RsBlue
import com.github.skydoves.colorpicker.compose.ColorEnvelope
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController

@Composable
fun ColorPicker(
    openDialog: MutableState<Boolean>,
    colorNew: MutableState<Color>
) {
    val controller = rememberColorPickerController()
    var color by remember { mutableStateOf(colorNew.value) }

    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onCloseRequest.
            openDialog.value = false
        },
        title = {
            Text(text = "Farbe auswählen")
        },
        text = {
            HsvColorPicker(
                modifier = Modifier
                    .width(250.dp)
                    .height(250.dp)
                    .padding(40.dp),
                controller = controller,
                onColorChanged = { colorEnvelope: ColorEnvelope ->
                        color = colorEnvelope.color
                }
            )

        },
        confirmButton = {
            Button(
                onClick = {
                    colorNew.value = color
                    openDialog.value = false
                }) {
                Text("Hinzufügen")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    openDialog.value = false
                }) {
                Text("Abbrechen")
            }
        }
    )
}
