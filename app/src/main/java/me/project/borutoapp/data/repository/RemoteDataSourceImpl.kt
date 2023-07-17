package me.project.borutoapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.project.borutoapp.data.local.HeroDatabase
import me.project.borutoapp.data.paging_source.HeroRemoteMediator
import me.project.borutoapp.data.remote.HeroService
import me.project.borutoapp.domain.models.Hero
import me.project.borutoapp.domain.repository.RemoteDataSource

class RemoteDataSourceImpl(
    private val service: HeroService,
    private val database: HeroDatabase
): RemoteDataSource  {
    private val heroDao = database.heroDao()

    @OptIn(ExperimentalPagingApi::class)
    override fun getAllData(): Flow<PagingData<Hero>> {
        val pagingSourceFactory = { heroDao.getAllHeroes() }
        return Pager(
            config = PagingConfig(pageSize = 3),
            remoteMediator = HeroRemoteMediator(
                service = service,
                database = database
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchData(): Flow<PagingData<Hero>> {
        TODO("Not yet implemented")
    }
}