package com.ilizma.curriculum.data.datasource

import com.ilizma.api.data.EnergySourcesApi
import com.ilizma.api.model.HistoricData
import com.ilizma.api.model.LiveData
import com.ilizma.curriculum.data.mapper.ChartStateMapper
import com.ilizma.curriculum.data.mapper.CurriculumVitaeMapper
import com.ilizma.curriculum.data.model.ChartState
import com.ilizma.curriculum.data.model.DashboardState
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class CurriculumDataSourceImpTest {

    @RelaxedMockK
    private lateinit var api: EnergySourcesApi

    @RelaxedMockK
    private lateinit var dashboardMapper: CurriculumVitaeMapper

    @RelaxedMockK
    private lateinit var chartMapper: ChartStateMapper

    private lateinit var dataSource: CurriculumDataSource
    private val unknownError = "unknownError"

    init {
        MockKAnnotations.init(this)
    }

    @BeforeEach
    private fun setup() {
        dataSource = CurriculumDataSourceImp(
            api = api,
            dashboardMapper = dashboardMapper,
            chartMapper = chartMapper,
            unknownError = unknownError,
        )
    }

    @Nested
    inner class GetDashboardState {

        @Test
        fun `given LiveData, when getDashboardState is called, then result should be the expected`() {
            // given
            val liveData = mockk<LiveData>()
            val expected = mockk<DashboardState.Success>()
            every { api.getLiveData() } returns Single.just(liveData)
            every { dashboardMapper.from(liveData) } returns expected

            // when
            val resultObserver = dataSource.getDashboardState()
                .observeOn(Schedulers.trampoline())
                .test()

            // then
            resultObserver.assertValue { it == expected }
        }

        @Test
        fun `given error, when getDashboardState is called, then result should be the expected`() {
            // given
            val errorMessage = "errorMessage"
            val exception = IllegalArgumentException(errorMessage)
            every { api.getLiveData() } returns Single.error(exception)

            // when
            val resultObserver = dataSource.getDashboardState()
                .observeOn(Schedulers.trampoline())
                .test()

            // then
            resultObserver.assertValue { it is DashboardState.Error }
        }

    }

    @Nested
    inner class GetChartState {

        @Test
        fun `given HistoricData list, when getChartState is called, then result should be the expected`() {
            // given
            val historicDataList = mockk<List<HistoricData>>()
            val expected = mockk<ChartState.Success>()
            every { api.getHistoricDataList() } returns Single.just(historicDataList)
            every { chartMapper.from(historicDataList) } returns expected

            // when
            val resultObserver = dataSource.getChartState()
                .observeOn(Schedulers.trampoline())
                .test()

            // then
            resultObserver.assertValue { it == expected }
        }

        @Test
        fun `given error, when getDashboardState is called, then result should be the expected`() {
            // given
            val errorMessage = "errorMessage"
            val exception = IllegalArgumentException(errorMessage)
            every { api.getHistoricDataList() } returns Single.error(exception)

            // when
            val resultObserver = dataSource.getChartState()
                .observeOn(Schedulers.trampoline())
                .test()

            // then
            resultObserver.assertValue { it is ChartState.Error }
        }

    }

}