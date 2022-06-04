package com.example.combeertition.data.database.competition

import androidx.room.*

@Dao
interface CompetitionDao {
    @Update
    suspend fun update(competition: CompetitionDb)

    @Insert
    suspend fun insert(competition: CompetitionDb)

    @Delete
    suspend fun delete(competition: CompetitionDb)

    @Query("SELECT * FROM competition")
    suspend fun getAll(): List<CompetitionWithTeamsAndRounds>

    @Query("SELECT * FROM competition WHERE competitionId = :id")
    suspend fun getById(id: String): CompetitionWithTeamsAndRounds?

}