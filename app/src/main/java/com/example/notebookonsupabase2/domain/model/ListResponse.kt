package com.example.notebookonsupabase2.domain.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class ListResponse(
    @Contextual val id: UUID,
    val title: String,
    val created_at: String
)