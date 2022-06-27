package com.example.combeertition.data.database.relations.teamplayer

import androidx.room.*
import com.example.combeertition.data.database.player.PlayerDb

@Dao
interface TeamPlayerDao {

    @Insert
    suspend fun insert(item: TeamPlayerDb)

    @Delete
    suspend fun delete(item: TeamPlayerDb)

    @Query("SELECT * FROM teamPlayer")
    suspend fun getAll(): List<TeamPlayerDb>

    @Query("SELECT * FROM teamPlayer WHERE teamPlayerId = :id")
    suspend fun getById(id: String): TeamPlayerDb?

    @Query("SELECT * FROM teamPlayer WHERE teamId = :id")
    suspend fun getByTeamId(id: String): List<TeamPlayerDb>

    @Query("SELECT * FROM teamPlayer WHERE playerId = :id")
    suspend fun getByPlayerId(id: String): List<TeamPlayerDb>
}