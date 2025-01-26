package com.example.notebookonsupabase2.domain.usecase

import com.example.notebookonsupabase2.domain.model.ListItem
import com.example.notebookonsupabase2.domain.repository.ListRepository
import java.util.UUID
import javax.inject.Inject

class AddListItemUseCase @Inject constructor(
    private val listRepository: ListRepository
) {
    suspend operator fun invoke(listId: UUID, content: String) {
        listRepository.addListItem(listId, content)
    }
}


