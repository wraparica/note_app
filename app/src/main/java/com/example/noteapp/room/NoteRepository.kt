package com.example.noteapp.room

import com.example.noteapp.room.entity.NoteEntity
import kotlinx.coroutines.flow.Flow


interface NoteRepository {
    suspend fun addItem(noteEntity: NoteEntity)
    suspend fun update(noteEntity: NoteEntity)
    suspend fun delete(noteEntity: NoteEntity)
    fun getAll(): Flow<List<NoteEntity>>
}