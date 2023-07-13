package me.project.borutoapp.domain.usecase

import kotlinx.coroutines.flow.Flow
import me.project.borutoapp.data.repository.Repository
import javax.inject.Inject

class ReadOnBoardingUseCase @Inject constructor(
    private val repository: Repository
) {
     operator fun invoke() : Flow<Boolean> {
        return repository.readOnBoardingState()
    }
}