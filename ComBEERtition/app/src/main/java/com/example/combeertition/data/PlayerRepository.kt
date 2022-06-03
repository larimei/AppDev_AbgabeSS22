package com.example.combeertition.data

import androidx.compose.ui.graphics.Color
import com.example.combeertition.App
import com.example.combeertition.R
import com.example.combeertition.data.database.player.PlayerDao
import com.example.combeertition.domain.model.Player
import com.example.combeertition.domain.model.PlayerId
import com.example.combeertition.data.database.player.playerFromDb
import com.example.combeertition.data.database.player.playerToDb


val playerRepository = PlayerRepository(App.database.playerDao())

class PlayerRepository
    (private val dao: PlayerDao) {
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

    suspend fun getAllPlayers(): List<Player> = dao.getAll().mapNotNull { playerFromDb(it) }

    suspend fun getPlayerById(id: PlayerId): Player? =
        dao.getById(id.value)?.let { playerFromDb(it) }

    suspend fun addPlayer(player: Player) {
        dao.insert(playerToDb(player))
    }

    suspend fun deletePlayer(player: Player) {
        dao.delete(playerToDb(player))
    }

    suspend fun updatePlayer(player: Player) {
        dao.update(playerToDb(player))
    }


}