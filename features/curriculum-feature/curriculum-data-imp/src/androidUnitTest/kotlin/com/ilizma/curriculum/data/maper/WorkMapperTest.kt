package com.ilizma.curriculum.data.maper

import com.ilizma.curriculum.data.mapper.WorkMapper
import com.ilizma.curriculum.data.model.WorkDTO
import com.ilizma.curriculum.domain.model.Work
import kotlin.test.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import com.ilizma.curriculum.data.model.Work as DataWork

class WorkMapperTest {

    private lateinit var mapper: WorkMapper

    @BeforeTest
    fun setup() {
        mapper = WorkMapper()
    }

    @Test
    fun `given WorkDTO, when from, then result should be the expected DataWork`() {
        // given
        val data = WorkDTO(
            title = "title",
            task = "task",
            company = "company",
            place = "place",
            start_date = "start_date",
            end_date = "end_date",
            currently = false,
        )
        val expected = DataWork(
            title = "title",
            task = "task",
            company = "company",
            place = "place",
            startDate = "start_date",
            endDate = "end_date",
            currently = false,
        )

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }

    @Test
    fun `given DataWork, when from, then result should be the expected Work`() {
        // given
        val data = DataWork(
            title = "title",
            task = "task",
            company = "company",
            place = "place",
            startDate = "start_date",
            endDate = "end_date",
            currently = false,
        )
        val expected = Work(
            title = "title",
            task = "task",
            company = "company",
            place = "place",
            startDate = "start_date",
            endDate = "end_date",
            currently = false,
        )

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }

}