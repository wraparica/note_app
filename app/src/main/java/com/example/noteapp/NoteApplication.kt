package com.example.noteapp

import android.app.Application
import com.example.noteapp.room.AppContainer
import com.example.noteapp.room.AppDataContainer

class NoteApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}