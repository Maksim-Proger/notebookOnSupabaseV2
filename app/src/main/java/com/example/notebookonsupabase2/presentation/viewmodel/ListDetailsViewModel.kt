package com.example.notebookonsupabase2.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject
import com.example.notebookonsupabase2.domain.model.ListItem
import com.example.notebookonsupabase2.domain.usecase.GetListItemsUseCase
import com.example.notebookonsupabase2.domain.usecase.AddListItemUseCase


@HiltViewModel
class ListDetailsViewModel @Inject constructor(
    private val getListItemsUseCase: GetListItemsUseCase,
    private val addListItemUseCase: AddListItemUseCase
) : ViewModel() {

    private val _items = MutableStateFlow<List<ListItem>>(emptyList())
    val items: StateFlow<List<ListItem>> = _items

    // Загружаем элементы списка
    fun loadListItems(listId: UUID) = viewModelScope.launch {
        try {
            val items = getListItemsUseCase(listId)
            _items.value = items
            Log.d("ListDetailsViewModel", "Items loaded: $items") // Логируем результат
        } catch (e: Exception) {
            Log.e("ListDetailsViewModel", "Error loading items", e) // Логируем ошибку
        }
    }

    fun addListItem(listId: UUID, content: String) = viewModelScope.launch {
        try {
            addListItemUseCase(listId, content)
            loadListItems(listId) // Перезагружаем список после добавления
        } catch (e: Exception) {
            // Обработка ошибки
        }
    }
}

