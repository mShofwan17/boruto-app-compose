package me.project.borutoapp.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import me.project.borutoapp.data.repository.Repository
import me.project.borutoapp.domain.models.Hero

class GetSelectedHeroUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(id: Int): Flow<Hero> {
        return flow {
            emit(repository.getSelectedHero(id))
        }.flowOn(Dispatchers.IO)
    }
}