package com.ilizma.education.presentation.model

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList

sealed interface EducationState {

    data class Success(
        val education: ImmutableList<Education>,
        val complementaryEducation: ImmutableList<ComplementaryEducation>,
    ) : EducationState

    @Immutable
    data class Error(
        val message: String,
    ) : EducationState

    data object Loading : EducationState

}