package com.example.notebookonsupabase2.domain.usecase

import com.example.notebookonsupabase2.domain.model.ListItem
import com.example.notebookonsupabase2.domain.repository.ListRepository
import java.util.UUID
import javax.inject.Inject

class GetListItemsUseCase @Inject constructor(
    private val repository: ListRepository
) {
    suspend operator fun invoke(listId: UUID): List<ListItem> {
        return repository.getListItems(listId)
    }
}
