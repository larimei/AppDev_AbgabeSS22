package com.example.combeertition.data.database.team

import androidx.room.*


@Dao
interface TeamDao {
    @Update
    suspend fun update(team: TeamDb)

    @Insert
    suspend fun insert(team: TeamDb)

    @Delete
    suspend fun delete(team: TeamDb)

    @Transaction
    @Query("SELECT * FROM team")
    suspend fun getAll(): List<TeamWithPlayers>

    @Transaction
    @Query("SELECT * FROM team WHERE teamId = :id")
    suspend fun getById(id: String): TeamWithPlayers?

}