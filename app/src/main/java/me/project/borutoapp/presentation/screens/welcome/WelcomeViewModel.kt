package me.project.borutoapp.presentation.screens.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.project.borutoapp.domain.usecase.GetUsecases
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val getUsecases: GetUsecases
) : ViewModel() {

    fun saveOnBoardingState(completed: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            getUsecases.saveOnBoardingUseCase(completed)
        }
    }
}