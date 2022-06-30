package com.example.combeertition.feature.player

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.combeertition.R
import com.example.combeertition.domain.model.PlayerId
import com.example.combeertition.feature.main.ui.navControllerGlobal
import com.example.combeertition.ui.theme.RsRed


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PlayerItem(player: PlayerUI) {
    Box(modifier = Modifier.padding(20.dp)) {
        Box(modifier = Modifier
            .size(130.dp)
            .shadow(10.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .background(
                Brush.verticalGradient(
                    listOf(
                        player.color,
                        Color.White
                    )
                )
            )
            .clickable {
                navControllerGlobal?.navigate(
                    "player/" + player.id.value
                )
            }
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth().fillMaxHeight()
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_player),
                    contentDescription = player.name,
                    modifier = Modifier
                        .size(70.dp)
                        .padding(8.dp),
                    tint = Color.Black
                )

                Text(
                    text = player.name,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}


