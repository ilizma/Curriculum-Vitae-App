package com.ilizma.work.presentation.model

sealed interface WorkIntent {

    data object Retry : WorkIntent
    data object Back : WorkIntent

}