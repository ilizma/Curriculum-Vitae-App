package com.ilizma.education.presentation.model

sealed class EducationIntent {

    data object Retry : EducationIntent()
    data object Back : EducationIntent()

}