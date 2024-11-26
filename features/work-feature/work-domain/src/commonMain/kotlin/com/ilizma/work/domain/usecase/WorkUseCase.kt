package com.ilizma.work.domain.usecase

import com.ilizma.work.domain.model.Work

interface WorkUseCase {

    suspend operator fun invoke(): List<Work>

}