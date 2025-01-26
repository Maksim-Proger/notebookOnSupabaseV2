package com.example.notebookonsupabase2.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.notebookonsupabase2.presentation.viewmodel.ListDetailsViewModel
import java.util.UUID


@Composable
fun ListDetailsScreen(listId: UUID, listTitle: String, viewModel: ListDetailsViewModel = hiltViewModel()) {
    val items by viewModel.items.collectAsState()

    // При первом запуске экрана загружаем элементы списка
    LaunchedEffect(listId) {
        viewModel.loadListItems(listId)
    }

    Column {
        Text(text = listTitle, style = MaterialTheme.typography.bodyMedium)

        // Если элементы списка есть, отображаем их в LazyColumn
        LazyColumn {
            items(items) { item ->
                Text(text = item.content)
            }
        }

        Button(onClick = { viewModel.addListItem(listId, "Новая заметка") }) {
            Text("Добавить заметку")
        }
    }
}

