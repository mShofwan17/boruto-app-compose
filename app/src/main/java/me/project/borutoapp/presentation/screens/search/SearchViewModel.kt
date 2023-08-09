package me.project.borutoapp.presentation.screens.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {
    private var _query = mutableStateOf("")
    val searchQuery get() = _query

    fun updateSearchQuery(query: String){
        _query.value = query
    }
}