package com.ilizma.work.presentation.model

sealed class WorkIntent {

    data object Retry : WorkIntent()
    data object Back : WorkIntent()

}