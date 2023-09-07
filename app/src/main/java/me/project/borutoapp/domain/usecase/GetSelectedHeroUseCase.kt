package me.project.borutoapp.domain.usecase

import me.project.borutoapp.data.repository.Repository
import me.project.borutoapp.domain.models.Hero

class GetSelectedHeroUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(id: Int): Hero {
        return repository.getSelectedHero(id)
    }
}