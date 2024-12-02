package com.ilizma.work.presentation.mapper

import com.ilizma.work.domain.model.Work
import com.ilizma.work.presentation.model.WorkState
import kotlinx.collections.immutable.persistentListOf
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import com.ilizma.work.presentation.model.Work as PresentationWork

class WorkMapperTest {

    private lateinit var mapper: WorkMapper

    @BeforeTest
    fun setup() {
        mapper = WorkMapper()
    }

    @Test
    fun `given Work list, when from, then result should be the expected Success WorkState`() {
        // given
        val data = Work(
            title = "title",
            task = "task",
            company = "company",
            place = "place",
            startDate = "start_date",
            endDate = "end_date",
            currently = false,
        ).let { listOf(it) }
        val expected = PresentationWork(
            title = "title",
            task = "task",
            company = "company",
            place = "place",
            startDate = "start_date",
            endDate = "end_date",
            currently = false,
        ).let { persistentListOf(it) }
            .let { WorkState.Success(it) }

        // when
        val result = mapper.from(data)

        // then
        assertEquals(expected, result)
    }

}