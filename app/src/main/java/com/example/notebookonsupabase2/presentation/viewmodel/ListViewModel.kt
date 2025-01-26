package com.example.notebookonsupabase2.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notebookonsupabase2.domain.model.ListModel
import com.example.notebookonsupabase2.domain.usecase.CreateListUseCase
import com.example.notebookonsupabase2.domain.usecase.GetAllListsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val createListUseCase: CreateListUseCase,
    private val getAllListsUseCase: GetAllListsUseCase
) : ViewModel() {

    private val _lists = MutableStateFlow<List<ListModel>>(emptyList())
    val lists: StateFlow<List<ListModel>> = _lists

    init {
        loadLists() // Загружаем списки при запуске
    }

    private fun loadLists() = viewModelScope.launch {
        try {
            val listsFromRepo = getAllListsUseCase()
            _lists.value = listsFromRepo
            Log.d("ListViewModel", "Lists loaded: $listsFromRepo") // Логируем результат
        } catch (e: Exception) {
            Log.e("ListViewModel", "Error loading lists", e) // Логируем ошибку
        }
    }

    fun createList(title: String) = viewModelScope.launch {
        try {
            val listId = createListUseCase(title)
            _lists.value = _lists.value + ListModel(listId, title, "2023-01-01")
        } catch (e: Exception) {
            // Обработка ошибки
        }
    }
}

