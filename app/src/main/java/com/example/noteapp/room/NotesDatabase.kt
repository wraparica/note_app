package com.example.noteapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.noteapp.room.dao.NotesDao
import com.example.noteapp.room.entity.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1,
    exportSchema = false
)

abstract class NoteDatabase: RoomDatabase() {
    abstract fun notesDao(): NotesDao

    companion object {
        @Volatile
        private var Instance: NoteDatabase? = null
        fun getDatabase(context: Context): NoteDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, NoteDatabase::class.java, "note_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}