package com.example.notebookonsupabase2.data.repository

import android.util.Log
import com.example.notebookonsupabase2.domain.model.ListItem
import com.example.notebookonsupabase2.domain.model.ListItemRequest
import com.example.notebookonsupabase2.domain.model.ListModel
import com.example.notebookonsupabase2.domain.model.ListResponse
import com.example.notebookonsupabase2.domain.repository.ListRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import java.util.UUID
import javax.inject.Inject

class ListRepositoryImpl @Inject constructor(
    private val supabaseClient: SupabaseClient
) : ListRepository {

    override suspend fun createList(title: String): UUID {
        return try {
            val result = supabaseClient.postgrest["lists"]
                .insert(mapOf("title" to title)) {
                    select(columns = Columns.list("id"))
                }
                .decodeSingle<ListResponse>() // Десериализуем в ListResponse
            result.id // Возвращаем ID
        } catch (e: Exception) {
            throw e // Обработка ошибок
        }
    }

    override suspend fun addListItem(listId: UUID, content: String) {
        try {
            val request = ListItemRequest(listId, content)
            supabaseClient.postgrest["list_items"]
                .insert(listOf(request))  // Передаем данные в виде списка
                .decodeSingle<Unit>()
        } catch (e: Exception) {
            Log.e("AddListItem", "Error while adding item: ${e.message}")
        }
    }

    override suspend fun getAllLists(): List<ListModel> {
        return try {
            val response = supabaseClient.postgrest["lists"]
                .select()
                .decodeList<ListModel>()
            response // Получаем список всех списков
        } catch (e: Exception) {
            emptyList() // В случае ошибки возвращаем пустой список
        }
    }

    override suspend fun getListItems(listId: UUID): List<ListItem> {
        return try {
            val items = supabaseClient.postgrest["list_items"]
                .select {
                    filter {
                        eq("list_id", listId)
                    }
                }
                .decodeList<ListItem>()
            Log.d("ListRepositoryImpl", "Fetched items: $items") // Логируем результат
            items
        } catch (e: Exception) {
            Log.e("ListRepositoryImpl", "Error fetching items: ${e.message}", e) // Логируем ошибку
            emptyList()
        }
    }
}