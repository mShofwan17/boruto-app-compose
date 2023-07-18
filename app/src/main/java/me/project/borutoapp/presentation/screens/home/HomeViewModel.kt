package me.project.borutoapp.presentation.screens.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import me.project.borutoapp.domain.usecase.GetUsecases
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getUsecases: GetUsecases
) : ViewModel() {
    val getAllHeroes = getUsecases.getAllHeroesUseCase()
}