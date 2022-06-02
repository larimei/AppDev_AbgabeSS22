package com.example.combeertition.domain.competition

import com.example.combeertition.data.competitionRepository

class GetCompetitionsUseCase {
    operator fun invoke() = competitionRepository.getAllCompetitions()
}