package com.example.combeertition.domain

import com.example.combeertition.data.teamRepository

class GetTeamsUseCase {
    operator fun invoke() = teamRepository.getAllTeams()
}