package me.project.borutoapp.presentation.screens.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.project.borutoapp.domain.models.Hero
import me.project.borutoapp.domain.usecase.GetUsecases
import me.project.borutoapp.domain.usecase.SearchHeroesUseCase
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val usecases: GetUsecases
) : ViewModel() {
    private var _query = mutableStateOf("")
    val searchQuery get() = _query

    private var _heroes = MutableStateFlow<PagingData<Hero>>(PagingData.empty())
    val heroes get() = _heroes

    fun updateSearchQuery(query: String){
        _query.value = query
    }

    fun searchHeroes(query: String){
        viewModelScope.launch {
            usecases.searchHeroesUseCase(query = query).cachedIn(this)
                .collectLatest {
                    _heroes.value = it
                }
        }
    }
}