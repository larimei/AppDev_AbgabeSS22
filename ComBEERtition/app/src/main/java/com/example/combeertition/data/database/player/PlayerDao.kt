package com.example.combeertition.data.database.player

import androidx.room.*

@Dao
interface PlayerDao {

    @Update
    suspend fun update(item: PlayerDb)

    @Insert
    suspend fun insert(player: PlayerDb)

    @Delete
    suspend fun delete(player: PlayerDb)

    @Query("SELECT * FROM player")
    suspend fun getAll(): List<PlayerDb>

    @Query("SELECT * FROM player WHERE playerId = :id")
    suspend fun getById(id: String): PlayerDb?
}