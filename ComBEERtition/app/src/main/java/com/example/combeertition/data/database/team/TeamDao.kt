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

    @Query("SELECT * FROM team")
    suspend fun getAll(): List<TeamPlayerRelation>

    @Query("SELECT * FROM team WHERE teamId = :id")
    suspend fun getById(id: String): TeamPlayerRelation?

    //TODO realation adden
}