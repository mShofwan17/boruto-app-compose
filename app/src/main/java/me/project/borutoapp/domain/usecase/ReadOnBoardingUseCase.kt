package me.project.borutoapp.domain.usecase

import kotlinx.coroutines.flow.Flow
import me.project.borutoapp.data.repository.Repository

class ReadOnBoardingUseCase(
    private val repository: Repository
) {
     operator fun invoke() : Flow<Boolean> {
        return repository.readOnBoardingState()
    }
}