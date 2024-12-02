package com.ilizma.curriculum.data.mapper

import com.ilizma.curriculum.data.model.WorkDTO
import com.ilizma.curriculum.domain.model.Work
import com.ilizma.curriculum.data.model.Work as DataWork

class WorkMapper {

    fun from(
        data: WorkDTO,
    ): DataWork = DataWork(
        title = data.title ?: throw nullParameterException("work.title"),
        task = data.task ?: "",
        company = data.company ?: throw nullParameterException("work.company"),
        place = data.place ?: "",
        startDate = data.start_date ?: "",
        endDate = data.end_date ?: "",
        currently = data.currently ?: false,
    )

    fun from(
        data: DataWork,
    ): Work = Work(
        title = data.title,
        task = data.task,
        company = data.company,
        place = data.place,
        startDate = data.startDate,
        endDate = data.endDate,
        currently = data.currently,
    )

    private fun nullParameterException(
        parameter: String
    ): IllegalArgumentException = IllegalArgumentException("$parameter can not be null")

}