package me.project.borutoapp.presentation.screens.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private val _selectedHero: MutableState<Hero?> = mutableStateOf(null)
    val selectedHero: State<Hero?> get() = _selectedHero

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
}