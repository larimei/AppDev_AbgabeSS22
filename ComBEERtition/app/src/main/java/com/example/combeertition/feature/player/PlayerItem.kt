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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.combeertition.R
import com.example.combeertition.domain.model.PlayerId
import com.example.combeertition.ui.theme.RsRed


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
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(player.icon),
                contentDescription = player.name,
                modifier = Modifier
                    .size(50.dp)
                    .padding(end = 8.dp),
                tint = player.color
            )
            Text(
                text = player.name,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Box(
                modifier = Modifier.align(Alignment.CenterVertically),
            ) {
                IconButton(
                    onClick = { deletePlayer(player.id) },
                ) {
                    Icon(painterResource(R.drawable.ic_baseline_delete_24),
                        contentDescription = "delete player",
                        tint = RsRed)
                }
            }
        }
    }
}

@Preview
@Composable
fun PlayerItem_Preview() {
    PlayerItem(
        PlayerUI(
            PlayerId("foo"),
            "Lara",
            R.drawable.ic_player,
            Color.Blue
        )
    ) {}
}
