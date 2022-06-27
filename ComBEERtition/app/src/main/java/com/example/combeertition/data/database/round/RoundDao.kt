package com.example.combeertition.data.database.round

import androidx.room.*
import com.example.combeertition.domain.model.Round

@Dao
interface RoundDao {
    @Update
    suspend fun update(round: RoundDb)

    @Insert
    suspend fun insert(round: RoundDb)

    @Delete
    suspend fun delete(round: RoundDb)

    @Query("SELECT * FROM round WHERE roundId = :id")
    suspend fun getById(id: String): RoundDb?

    @Query("SELECT * FROM round WHERE competitionId = :id")
    suspend fun getByCompetitionId(id: String): List<RoundDb>
}