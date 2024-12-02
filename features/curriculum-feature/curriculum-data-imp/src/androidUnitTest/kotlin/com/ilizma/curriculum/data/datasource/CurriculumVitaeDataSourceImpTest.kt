package com.ilizma.curriculum.data.datasource

import com.ilizma.curriculum.data.mapper.CurriculumVitaeMapper
import com.ilizma.curriculum.data.model.CurriculumVitae
import com.ilizma.curriculum.data.model.CurriculumVitaeDTO
import dev.gitlive.firebase.firestore.CollectionReference
import dev.gitlive.firebase.firestore.DocumentSnapshot
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.QuerySnapshot
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class CurriculumVitaeDataSourceImpTest {

    private val cvId = "cvId"

    @RelaxedMockK
    private lateinit var curriculumVitaeDTO: CurriculumVitaeDTO

    @RelaxedMockK
    private lateinit var curriculumVitae: CurriculumVitae

    @RelaxedMockK
    private lateinit var firestore: FirebaseFirestore

    @RelaxedMockK
    private lateinit var mapper: CurriculumVitaeMapper

    @RelaxedMockK
    private lateinit var querySnapshot: QuerySnapshot

    private lateinit var dataSource: CurriculumVitaeDataSource

    init {
        MockKAnnotations.init(this)

        every { querySnapshot.documents } returns listOf(
            mockk<DocumentSnapshot> {
                every { id } returns cvId
                every { data<CurriculumVitaeDTO>() } returns curriculumVitaeDTO
            }
        )
        every { firestore.collection(CVS_COLLECTION) } returns mockk<CollectionReference> {
            coEvery { get() } returns querySnapshot
        }
        every { mapper.from(curriculumVitaeDTO) } returns curriculumVitae
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeTest
    fun setup() {
        dataSource = CurriculumVitaeDataSourceImp(
            cvId = cvId,
            firestore = firestore,
            mapper = mapper,
        )
    }

    @Test
    fun `given a correct cvID, when get, then result should be the expected`() =
        runTest {
            // given
            val expected = curriculumVitae

            // when
            val result = dataSource.get()

            // then
            assertEquals(expected, result)
        }

    @Test
    fun `given an incorrect cvID, when get, then should be throw NoSuchElementException`() =
        runTest {
            // given
            every { querySnapshot.documents } returns emptyList()

            // when, then
            assertFailsWith(NoSuchElementException::class, "No CV found") { dataSource.get() }
        }

}