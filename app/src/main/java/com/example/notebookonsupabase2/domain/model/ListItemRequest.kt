package com.example.notebookonsupabase2.domain.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class ListItemRequest(
    @Contextual val list_id: UUID,
    val content: String
)