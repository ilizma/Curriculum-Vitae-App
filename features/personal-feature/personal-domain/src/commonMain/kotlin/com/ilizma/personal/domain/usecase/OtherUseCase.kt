package com.ilizma.personal.domain.usecase

import com.ilizma.personal.domain.model.Other

interface OtherUseCase {

    suspend operator fun invoke(): List<Other>

}