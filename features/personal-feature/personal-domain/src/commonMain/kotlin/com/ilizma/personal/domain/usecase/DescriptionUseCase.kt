package com.ilizma.personal.domain.usecase

interface DescriptionUseCase {

    suspend operator fun invoke(): String

}