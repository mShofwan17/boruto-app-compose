package me.project.borutoapp.data.repository

import kotlinx.coroutines.flow.Flow
import me.project.borutoapp.domain.repository.DataStoreOperations
import javax.inject.Inject

class Repository @Inject constructor(
    private val datastore: DataStoreOperations
) {
    suspend fun saveOnBoardingState(completed: Boolean) {
        datastore.saveOnBoardingState(completed)
    }

    fun readOnBoardingState() : Flow<Boolean>{
        return datastore.readOnBoardingState()
    }

}