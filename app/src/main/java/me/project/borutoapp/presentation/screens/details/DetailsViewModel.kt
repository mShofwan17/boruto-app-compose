package me.project.borutoapp.presentation.screens.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.project.borutoapp.domain.models.Hero
import me.project.borutoapp.domain.usecase.GetUsecases
import me.project.borutoapp.utils.Constant.ID
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCases: GetUsecases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _selectedHero: MutableStateFlow<Hero?> = MutableStateFlow(null)
    val selectedHero: StateFlow<Hero?> get() = _selectedHero

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent: SharedFlow<UiEvent> get() = _uiEvent.asSharedFlow()

    private val _colorPalette: MutableState<Map<String, String>> = mutableStateOf(emptyMap())
    val colorPalette: State<Map<String, String>> get() = _colorPalette

    init {
        val id: Int? = savedStateHandle[ID]
        id?.let {
            getSelectedHero(it)
        }
    }

    private fun getSelectedHero(id: Int) {
        viewModelScope.launch {
            useCases.getSelectedHeroUseCase(id)
                .collectLatest {
                    _selectedHero.value = it
                }
        }
    }

    fun generateColorPalette() {
        viewModelScope.launch {
            _uiEvent.emit(UiEvent.GenerateColorPalette)
        }
    }

    fun setColorPalette(color: Map<String, String>) {
        _colorPalette.value = color
    }
}

sealed class UiEvent {
    object GenerateColorPalette : UiEvent()
}