package com.example.combeertition.data

import com.example.combeertition.R
import com.example.combeertition.domain.model.Competition
import com.example.combeertition.domain.model.CompetitionId

val competitionRepository = CompetitionRepository()

class CompetitionRepository {
    private var allCompetitions = listOfNotNull(
        Competition.create(
            id = CompetitionId("ac"),
            name = "Competition 1",
            teams = listOf("a"),
            mode="",
            rounds = emptyList(),
            icon = R.drawable.ic_competition),
        Competition.create(
            id = CompetitionId("bc"),
            name = "Competition 2",
            teams = emptyList(),
            mode="",
            rounds = emptyList(),
            icon = R.drawable.ic_competition,
        ),
        Competition.create(
            id = CompetitionId("cc"),
            name = "Competition 3",
            teams = emptyList(),
            mode="",
            rounds = emptyList(),
            icon = R.drawable.ic_competition,
        ),
        Competition.create(
            id = CompetitionId("ccc"),
            name = "Competition 4",
            teams = emptyList(),
            mode="",
            rounds = emptyList(),
            icon = R.drawable.ic_competition,
        ),
        Competition.create(
            id = CompetitionId("ec"),
            name = "Competition 5",
            teams = emptyList(),
            mode="",
            rounds = emptyList(),
            icon = R.drawable.ic_competition,
        ),
        Competition.create(
            id = CompetitionId("fc"),
            name = "Competition 6",
            teams = emptyList(),
            mode="",
            rounds = emptyList(),
            icon = R.drawable.ic_competition,
            ),
    )

    fun getAllCompetitions() = allCompetitions

    fun getCompetitionById(id: CompetitionId): Competition? = allCompetitions.firstOrNull {
        it.id == id
    }

    fun updateCompetitions(newCompetitions: List<Competition>): List<Competition> {
        allCompetitions = newCompetitions
        return getAllCompetitions()
    }

}