package me.project.borutoapp.domain.usecase

import me.project.borutoapp.data.repository.Repository
import javax.inject.Inject

class SaveOnBoardingUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(completed: Boolean) {
        repository.saveOnBoardingState(completed = completed)
    }
}