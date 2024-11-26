package com.ilizma.curriculum.domain.model

data class Work(
    val title: String,
    val task: String,
    val company: String,
    val place: String,
    val startDate: String,
    val endDate: String,
    val currently: Boolean,
)