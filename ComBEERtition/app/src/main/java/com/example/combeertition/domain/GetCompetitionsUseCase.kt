package com.example.combeertition.domain

import com.example.combeertition.data.competitionRepository

class GetCompetitionsUseCase {
    operator fun invoke() = competitionRepository.getAllCompetitions()
}