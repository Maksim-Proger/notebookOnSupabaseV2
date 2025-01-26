package com.example.notebookonsupabase2.di

import com.example.notebookonsupabase2.data.repository.ListRepositoryImpl
import com.example.notebookonsupabase2.domain.model.UUIDSerializer
import com.example.notebookonsupabase2.domain.repository.ListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.serializer.KotlinXSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSupabaseClient(): SupabaseClient {
        val json = Json {
            serializersModule = SerializersModule {
                contextual(UUIDSerializer) // Регистрируем кастомный сериализатор
            }
        }

        return createSupabaseClient(
            supabaseUrl = "https://zfjldzjxtlwlaiklcfkt.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inpmamxkemp4dGx3bGFpa2xjZmt0Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3Mzc3Mjg1MDAsImV4cCI6MjA1MzMwNDUwMH0.3ClNL-Lg7qso6gqJ331HIJ7YxjRD3a1t79WkU2Jz8R0"
        ) {
            install(Postgrest)
            defaultSerializer = KotlinXSerializer(json) // Передаём Json вместо SerializersModule
        }
    }

    @Provides
    @Singleton
    fun provideListRepository(supabaseClient: SupabaseClient): ListRepository {
        return ListRepositoryImpl(supabaseClient)
    }
}