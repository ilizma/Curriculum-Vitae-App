package com.ilizma.personal.domain.usecase

interface SkillsUseCase {

    suspend operator fun invoke(): List<String>

}