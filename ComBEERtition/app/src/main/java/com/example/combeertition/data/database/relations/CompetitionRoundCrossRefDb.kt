package com.example.combeertition.data.database.relations

import androidx.room.Entity

@Entity(primaryKeys = ["competitionId", "roundId"])
data class CompetitionRoundCrossRefDb(
    val competitionId: String,
    val roundId: String

)