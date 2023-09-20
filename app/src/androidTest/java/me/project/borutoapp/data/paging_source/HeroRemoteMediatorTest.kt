package me.project.borutoapp.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator.*
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.test.runTest
import me.project.borutoapp.data.local.HeroDatabase
import me.project.borutoapp.data.remote.FakeBorutoApi2
import me.project.borutoapp.domain.models.Hero
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

@OptIn(ExperimentalPagingApi::class)
class HeroRemoteMediatorTest {
    private lateinit var borutoApi2: FakeBorutoApi2
    private lateinit var borutoDatabase: HeroDatabase

    @Before
    fun setup() {
        borutoApi2 = FakeBorutoApi2()
        borutoDatabase = HeroDatabase.create(
            context = ApplicationProvider.getApplicationContext(),
            useInMemory = true
        )
    }

    @After
    fun cleanup() {
        borutoDatabase.clearAllTables()
    }

    @Test
    fun refreshLoadReturnsSuccessResultWhenMoreDataIsPresent() =
        runTest {
            val remoteMediator = HeroRemoteMediator(
                borutoApi2,
                borutoDatabase
            )

            val pagingState = PagingState<Int, Hero>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 3),
                leadingPlaceholderCount = 0
            )

            val result = remoteMediator.load(LoadType.REFRESH, pagingState)
            assertTrue(result is MediatorResult.Success)
            assertFalse((result as MediatorResult.Success).endOfPaginationReached)
        }

    @Test
    fun refreshLoadSuccessAndEndofPaginationTrueWhenNoMoreData() =
        runTest {
            borutoApi2.clearData()
            val remoteMediator = HeroRemoteMediator(
                borutoApi2,
                borutoDatabase
            )

            val pagingState = PagingState<Int, Hero>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 3),
                leadingPlaceholderCount = 0
            )

            val result = remoteMediator.load(LoadType.REFRESH, pagingState)
            assertTrue(result is MediatorResult.Success)
            assertTrue((result as MediatorResult.Success).endOfPaginationReached)
        }

    @Test
    fun refreshLoadReturnsErrorResultWhenErrorOccurs() =
        runTest {
            borutoApi2.addExecption()
            val remoteMediator = HeroRemoteMediator(
                borutoApi2,
                borutoDatabase
            )

            val pagingState = PagingState<Int, Hero>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 3),
                leadingPlaceholderCount = 0
            )

            val result = remoteMediator.load(LoadType.REFRESH, pagingState)
            assertTrue(result is MediatorResult.Error)
        }
}