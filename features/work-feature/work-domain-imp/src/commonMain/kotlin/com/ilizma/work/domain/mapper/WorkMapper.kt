package com.ilizma.work.domain.mapper

import com.ilizma.curriculum.domain.model.CurriculumVitae
import com.ilizma.work.domain.model.Work

class WorkMapper {

    fun from(
        state: CurriculumVitae
    ): List<Work> = state.work.map {
        Work(
            title = it.title,
            task = it.task,
            company = it.company,
            currently = it.currently,
            place = it.place,
            startDate = it.startDate,
            endDate = it.endDate,
        )
    }
}