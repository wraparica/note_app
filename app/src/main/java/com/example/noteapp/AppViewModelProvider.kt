package com.example.noteapp

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            NoteViewModel(
                receiptApplication().container.itemRepository
            )
        }

    }
}

fun CreationExtras.receiptApplication(): NoteApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as NoteApplication)
