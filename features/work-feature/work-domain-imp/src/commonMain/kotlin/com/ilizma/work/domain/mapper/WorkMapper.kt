package com.ilizma.work.domain.mapper

import com.ilizma.work.domain.model.Work
import com.ilizma.curriculum.domain.model.Work  as CVWork

class WorkMapper {

    fun from(
        data: List<CVWork>
    ): List<Work> = data.map {
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