package me.project.borutoapp.domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.project.borutoapp.data.repository.Repository
import me.project.borutoapp.domain.models.Hero

class GetAllHeroesUseCase(
    private val repository: Repository
) {
    operator fun invoke() : Flow<PagingData<Hero>>{
        return repository.getAllHeroes()
    }
}