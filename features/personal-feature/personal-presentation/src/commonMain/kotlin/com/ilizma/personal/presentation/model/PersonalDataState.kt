package com.ilizma.personal.presentation.model

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList

sealed class PersonalDataState {

    @Immutable
    data class Success(
        val photo: String,
        val name: String,
        val surname: String,
        val surname2: String,
        val phone: String,
        val email: String,
        val address: String,
        val city: String,
        val postalCode: String,
        val bornDate: String,
        val description: String,
        val skills: ImmutableList<String>,
        val other: ImmutableList<Other>,
    ) : PersonalDataState()

    @Immutable
    data class Error(
        val message: String,
    ) : PersonalDataState()

    data object Loading : PersonalDataState()

}