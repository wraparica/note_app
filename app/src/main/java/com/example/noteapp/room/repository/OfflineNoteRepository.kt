package com.example.noteapp.room.repository

import com.example.noteapp.room.NoteRepository
import com.example.noteapp.room.dao.NotesDao
import com.example.noteapp.room.entity.NoteEntity

class OfflineNoteRepository(private val notesDao: NotesDao) : NoteRepository {
    override suspend fun addItem(noteEntity: NoteEntity) = notesDao.insert(noteEntity)
    override fun getAll() = notesDao.getAll()
    override suspend fun update(noteEntity: NoteEntity) = notesDao.update(noteEntity)
    override suspend fun delete(noteEntity: NoteEntity) = notesDao.delete(noteEntity)
}