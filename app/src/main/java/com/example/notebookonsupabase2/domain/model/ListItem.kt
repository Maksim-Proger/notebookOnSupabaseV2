package com.example.notebookonsupabase2.domain.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class ListItem(
    @Contextual val id: UUID,
    @SerialName("list_id") @Contextual val listId: UUID,
    val content: String,
    @SerialName("created_at") val createdAt: String
)