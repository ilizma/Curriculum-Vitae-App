package com.ilizma.personal.presentation.model

sealed class PersonalDataIntent {

    data class Phone(val phone: String) : PersonalDataIntent()
    data class Email(val email: String) : PersonalDataIntent()
    data object Retry : PersonalDataIntent()
    data object Back : PersonalDataIntent()

}