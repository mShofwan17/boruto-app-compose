package me.project.borutoapp.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import me.project.borutoapp.domain.usecase.GetUsecases
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getUsecases: GetUsecases
) : ViewModel() {
    private val _onBoardingCompleted = MutableStateFlow(false)
    val onBoardingCompleted get() = _onBoardingCompleted.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _onBoardingCompleted.value = getUsecases.readOnBoardingUseCase().stateIn(viewModelScope).value
        }
    }
}