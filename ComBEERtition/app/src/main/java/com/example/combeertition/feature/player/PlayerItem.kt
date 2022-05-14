package com.example.combeertition.feature.player

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.combeertition.R
import com.example.combeertition.domain.model.PlayerId


@Composable
fun PlayerItem(player: PlayerUI, deletePlayer: (id: PlayerId) -> Unit) {
    Card(
        elevation = 3.dp,
        modifier = Modifier.padding(8.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Image(
                painter = painterResource(player.icon),
                contentDescription = player.name,
                modifier = Modifier
                    .size(50.dp)
                    .padding(end = 8.dp),
            )
            Column(
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = player.name,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Box(
                modifier = Modifier.align(Alignment.CenterVertically),
            ) {
                IconButton(
                    onClick = { deletePlayer(player.id) },
                ) {
                    Icon(painterResource(R.drawable.ic_baseline_delete_outline_24), contentDescription = "delete player")
                }
            }
        }
    }
}

@Preview
@Composable
fun ProductItem_Preview() {
    PlayerItem(
        PlayerUI(
            PlayerId("foo"),
            "Lara",
            R.drawable.ic_player,
        )
    ) {}
}
