package com.example.combeertition.feature.competitions.detail


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.combeertition.domain.model.CompetitionId


@Composable
fun SaveOverlay(
    openDialog: MutableState<Boolean>,
    onUpdateCompetition: (competitionId: CompetitionId, name: String, color: Color, teams: List<String>, mode: String) -> Unit,
    competitionId: CompetitionId,
    name: String,
    color: Color,
    teams: List<String>,
    mode: String
) {

    SaveOverlayUi(openDialog, onUpdateCompetition, competitionId, name, color, teams, mode)
}

@Composable
fun SaveOverlayUi(
    openDialog: MutableState<Boolean>,
    onUpdateCompetition: (competitionId: CompetitionId, name: String, color: Color, teams: List<String>, mode: String) -> Unit,
    competitionId: CompetitionId,
    name: String,
    color: Color,
    teams: List<String>,
    mode: String
) {

    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onCloseRequest.
            openDialog.value = false
        },
        title = {
            Text(text = "Achtung")
        },
        text = {
            Box(
                modifier = Modifier
                    .wrapContentSize(align = Alignment.TopStart)
            ) {
                Text(text = "Beim Speichern werden die Runden neu erstellt und deine Daten somit überschrieben. Willst du das?")
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    openDialog.value = false
                    onUpdateCompetition(competitionId, name, color, teams, mode)
                }) {
                Text("Ja klarifari")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    openDialog.value = false
                }) {
                Text("Nope")
            }
        }
    )
}