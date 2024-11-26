package com.ilizma.work.presentation.mapper

import com.ilizma.work.domain.model.Work
import com.ilizma.work.presentation.model.WorkState
import kotlinx.collections.immutable.toImmutableList
import com.ilizma.work.presentation.model.Work as PresentationWork

class WorkMapper {

    fun from(
        data: List<Work>,
    ): WorkState.Success = data.map {
        PresentationWork(
            title = it.title,
            task = it.task,
            company = it.company,
            currently = it.currently,
            place = it.place,
            startDate = it.startDate,
            endDate = it.endDate,
        )
    }.let { it.toImmutableList() }
        .let { WorkState.Success(it) }

}