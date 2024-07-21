package com.example.noteapp.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.noteapp.room.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(noteEntity: NoteEntity)

    @Query("Select * from notes")
    fun getAll(): Flow<List<NoteEntity>>

    @Update
    suspend fun update(noteEntity: NoteEntity)

    @Delete
    suspend fun delete(noteEntity: NoteEntity)

}