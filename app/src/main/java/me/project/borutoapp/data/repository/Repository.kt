package me.project.borutoapp.data.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.project.borutoapp.domain.models.Hero
import me.project.borutoapp.domain.repository.DataStoreOperations
import me.project.borutoapp.domain.repository.RemoteDataSource
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val datastore: DataStoreOperations
) {
    suspend fun saveOnBoardingState(completed: Boolean) {
        datastore.saveOnBoardingState(completed)
    }

    fun readOnBoardingState() : Flow<Boolean>{
        return datastore.readOnBoardingState()
    }

    fun getAllHeroes(): Flow<PagingData<Hero>>{
        return remoteDataSource.getAllData()
    }

    fun searchHeroes(query: String): Flow<PagingData<Hero>>{
        return remoteDataSource.searchData(query)
    }

}