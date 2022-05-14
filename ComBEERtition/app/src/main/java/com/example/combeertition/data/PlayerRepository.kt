package com.example.combeertition.data

import androidx.compose.ui.graphics.Color
import com.example.combeertition.R
import com.example.combeertition.domain.model.Player
import com.example.combeertition.domain.model.PlayerId

val playerRepository = PlayerRepository()

class PlayerRepository {
    private var allPlayers = listOfNotNull(
        Player.create(
            id = PlayerId("a"),
            name = "Lara Miester",
            icon = R.drawable.ic_player,
            Color.Cyan
        ),
        Player.create(
            id = PlayerId("b"),
            name = "Lara aerg",
            icon = R.drawable.ic_player,
            Color.Cyan
        ),
        Player.create(
            id = PlayerId("c"),
            name = "Lara seg",
            icon = R.drawable.ic_player,
            Color.Cyan
        ),
        Player.create(
            id = PlayerId("d"),
            name = "Lara eaerhaerh",
            icon = R.drawable.ic_player,
            Color.Cyan
        ),
        Player.create(
            id = PlayerId("e"),
            name = "Laaerhra Miester",
            icon = R.drawable.ic_player,
            Color.Cyan
        ),
        Player.create(
            id = PlayerId("f"),
            name = "Lara Miester",
            icon = R.drawable.ic_player,
            Color.Cyan
        ),
    )

    fun getAllPlayers() = allPlayers

    fun getPlayerById(id: PlayerId): Player? = allPlayers.firstOrNull {
        it.id == id
    }


    fun updatePlayers(newPlayers: List<Player>): List<Player> {
        allPlayers = newPlayers
        return getAllPlayers()
    }

}