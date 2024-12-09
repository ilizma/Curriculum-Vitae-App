package com.ilizma.education.presentation.model

sealed interface EducationIntent {

    data object Retry : EducationIntent
    data object Back : EducationIntent

}