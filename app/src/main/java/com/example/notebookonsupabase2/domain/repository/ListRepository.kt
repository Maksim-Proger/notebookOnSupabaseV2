package com.example.notebookonsupabase2.domain.repository

import com.example.notebookonsupabase2.domain.model.ListItem
import com.example.notebookonsupabase2.domain.model.ListModel
import java.util.UUID

interface ListRepository {
    suspend fun createList(title: String): UUID
    suspend fun addListItem(listId: UUID, content: String)
    suspend fun getAllLists(): List<ListModel>
    suspend fun getListItems(listId: UUID): List<ListItem>
}