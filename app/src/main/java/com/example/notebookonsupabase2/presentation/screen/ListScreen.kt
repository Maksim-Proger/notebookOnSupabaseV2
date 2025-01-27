package com.example.notebookonsupabase2.presentation.screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.notebookonsupabase2.presentation.viewmodel.ListViewModel

@Composable
fun ListScreen(navController: NavController) {
    val viewModel: ListViewModel = hiltViewModel()
    val lists by viewModel.lists.collectAsState()

    LaunchedEffect(Unit) {
        Log.d("ListScreen", "Lists loaded: $lists") // Логируем загруженные списки
    }

    Column {
        Button(onClick = { viewModel.createList("Новый список") }) {
            Text("Создать список")
        }

        // Проверка на пустоту списка
        if (lists.isNotEmpty()) {
            LazyColumn {
                items(lists) { list ->
                    Text(
                        text = list.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate("listDetails/${list.id}/${list.title}")
                            }
                            .padding(16.dp)
                    )
                }
            }
        } else {
            Text("Списки не найдены")
        }
    }
}


