package com.ilizma.work.presentation.model

sealed interface WorkScreenNavigationAction {

    data object Back : WorkScreenNavigationAction

}