package com.example.noteapp.room

import android.content.Context
import com.example.noteapp.room.repository.OfflineNoteRepository

interface AppContainer {
    val itemRepository: NoteRepository
}

class AppDataContainer(private val context: Context): AppContainer {

    override val itemRepository: OfflineNoteRepository by lazy {
        OfflineNoteRepository(NoteDatabase.getDatabase(context).notesDao())
    }
}