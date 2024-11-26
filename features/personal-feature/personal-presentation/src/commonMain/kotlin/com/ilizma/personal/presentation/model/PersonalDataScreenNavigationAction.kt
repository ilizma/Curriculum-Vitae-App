package com.ilizma.personal.presentation.model

sealed class PersonalDataScreenNavigationAction {

    data class Phone(val phone: String) : PersonalDataScreenNavigationAction()
    data class Email(val email: String) : PersonalDataScreenNavigationAction()
    data object Back : PersonalDataScreenNavigationAction()

}