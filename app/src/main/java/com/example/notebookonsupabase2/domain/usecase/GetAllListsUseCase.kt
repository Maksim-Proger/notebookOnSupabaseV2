package com.example.notebookonsupabase2.domain.usecase

import android.util.Log
import com.example.notebookonsupabase2.domain.model.ListModel
import com.example.notebookonsupabase2.domain.repository.ListRepository
import javax.inject.Inject

class GetAllListsUseCase @Inject constructor(
    private val listRepository: ListRepository
) {

    suspend operator fun invoke(): List<ListModel> {
        val lists = listRepository.getAllLists()
        Log.d("GetAllListsUseCase", "Lists fetched: $lists") // Логируем результат
        return lists
    }
}

