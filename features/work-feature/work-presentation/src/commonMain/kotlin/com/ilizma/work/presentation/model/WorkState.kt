package com.ilizma.work.presentation.model

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList

sealed interface WorkState {

    data class Success(
        val list: ImmutableList<Work>,
    ) : WorkState

    @Immutable
    data class Error(
        val message: String,
    ) : WorkState

    data object Loading : WorkState

}