package me.project.borutoapp.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.project.borutoapp.domain.models.Hero

interface RemoteDataSource {
    fun getAllData(): Flow<PagingData<Hero>>
    fun searchData(): Flow<PagingData<Hero>>
}