package com.example.notebookonsupabase2.domain.usecase

import com.example.notebookonsupabase2.data.repository.ListRepositoryImpl
import java.util.UUID
import javax.inject.Inject

class CreateListUseCase @Inject constructor(
    private val repository: ListRepositoryImpl
) {
    suspend operator fun invoke(title: String): UUID {
        return repository.createList(title)
    }
}