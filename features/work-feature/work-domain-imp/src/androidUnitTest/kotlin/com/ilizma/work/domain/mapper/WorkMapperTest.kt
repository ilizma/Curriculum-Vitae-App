package com.ilizma.work.domain.mapper

import com.ilizma.work.domain.model.Work
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import com.ilizma.curriculum.domain.model.Work as CVWork

class WorkMapperTest {

    private lateinit var mapper: WorkMapper

    @BeforeTest
    fun setup() {
        mapper = WorkMapper()
    }

    @Test
    fun `given CVWork, when from, then result should be the expected Work`() {
        // given
        val data = CVWork(
            title = "title",
            task = "task",
            company = "company",
            place = "place",
            startDate = "start_date",
            endDate = "end_date",
            currently = false,
        ).let { listOf(it) }
        val expected = Work(
            title = "title",
            task = "task",
            company = "company",
            place = "place",
            startDate = "start_date",
            endDate = "end_date",
            currently = false,
        ).let { listOf(it) }

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }
    
}