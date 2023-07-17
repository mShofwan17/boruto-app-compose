package me.project.borutoapp.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import me.project.borutoapp.data.local.HeroDatabase
import me.project.borutoapp.data.remote.HeroService
import me.project.borutoapp.domain.models.Hero
import me.project.borutoapp.domain.models.HeroRemoteKey
import java.lang.Exception
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class HeroRemoteMediator @Inject constructor(
    private val service: HeroService,
    private val database: HeroDatabase
) : RemoteMediator<Int, Hero>() {
    private val heroDao = database.heroDao()
    private val remoteKeyDao = database.heroRemoteKeyDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Hero>): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKey = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKey?.nextPage?.minus(1) ?: 1
                }

                LoadType.PREPEND -> {
                    val remoteKey = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKey?.prevPage
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKey != null)
                    prevPage
                }

                LoadType.APPEND -> {
                    val remoteKey = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKey?.nextPage
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKey != null)
                    nextPage
                }
            }
            val response = service.getAllHeroes(page = page)
            if (response.heroes.isNotEmpty()) {
                database.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        heroDao.deleteAllHeroes()
                        remoteKeyDao.deleteAllRemoteKey()
                    }

                    val prevPage = response.prevPage
                    val nextPage = response.nextPage
                    val keys = response.heroes.map {
                        HeroRemoteKey(
                            id = it.id,
                            prevPage = prevPage,
                            nextPage = nextPage
                        )
                    }
                    remoteKeyDao.addAllRemoteKeys(keys)
                    heroDao.addHero(response.heroes)
                }
            }

            MediatorResult.Success(endOfPaginationReached = response.nextPage == null)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Hero>): HeroRemoteKey? {
        return state.anchorPosition?.let {
            state.closestItemToPosition(anchorPosition = it)?.id?.let { id ->
                remoteKeyDao.getRemoteKey(id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Hero>): HeroRemoteKey? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { hero ->
            remoteKeyDao.getRemoteKey(hero.id)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Hero>): HeroRemoteKey? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { hero ->
            remoteKeyDao.getRemoteKey(hero.id)
        }
    }
}