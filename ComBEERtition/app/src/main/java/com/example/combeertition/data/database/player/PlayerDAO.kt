package com.example.combeertition.data.database.player

import androidx.room.*

@Dao
interface PlayerDAO {

    @Update
    suspend fun update(item: PlayerDB)

    @Insert
    suspend fun insert(player: PlayerDB)

    @Delete
    suspend fun delete(player: PlayerDB)

    @Query("SELECT * FROM player")
    suspend fun getAll(): List<PlayerDB>

    @Query("SELECT * FROM player WHERE playerId = :id")
    suspend fun getById(id: String): PlayerDB?
}