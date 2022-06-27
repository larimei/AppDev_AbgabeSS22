package com.example.combeertition.feature.components


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun DeleteOverlay(
    openDialog: MutableState<Boolean>,
    text: String
) {

    DeleteOverlayUi(openDialog, text)
}

@Composable
fun DeleteOverlayUi(
    openDialog: MutableState<Boolean>,
    text: String
) {

    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onCloseRequest.
            openDialog.value = false
        },
        title = {
            Text(text = "Löschen ist nicht möglich")
        },
        text = {
            Box(
                modifier = Modifier
                    .wrapContentSize(align = Alignment.TopStart)
            ) {
                Text("Ihr $text ist noch in mindestens einem Team und eventuell sogar in einem Turnier. Lösche erst das, um den Spieler zu entfernen.")
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    openDialog.value = false
                }) {
                Text("Alles klarifari")
            }
        }
    )
}