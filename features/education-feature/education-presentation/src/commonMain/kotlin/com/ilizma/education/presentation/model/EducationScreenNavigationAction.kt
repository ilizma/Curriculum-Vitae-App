package com.ilizma.education.presentation.model

sealed interface EducationScreenNavigationAction {

    data object Back : EducationScreenNavigationAction

}