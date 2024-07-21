package com.example.noteapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.room.NoteRepository
import com.example.noteapp.room.entity.NoteEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NoteViewModel(
    private val noteRepository: NoteRepository
) : ViewModel() {
    private val _title = MutableStateFlow("")
    private val _content = MutableStateFlow("")
    private val _isUpdate = MutableStateFlow(false)
    private val _sort = MutableStateFlow(0)
    private val _selectedItem = MutableStateFlow(NoteEntity())
    private val _notes = noteRepository.getAll()

    fun updateSelectedId(noteEntity: NoteEntity) {
        _title.update { noteEntity.title }
        _content.update { noteEntity.content }
        _selectedItem.update { noteEntity }
        _isUpdate.update { true }
    }
    val notes = combine(_notes, _sort) {
        list, sort ->
        NoteState(
            notes = when (sort) {
                0 -> list.sortedByDescending { it.title }
                else -> list.sortedBy { it.title }
            }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = NoteState()
    )
    fun clear() {
        _title.update { "" }
        _content.update { "" }
        _isUpdate.update { false }
    }
    fun updateTitle(name: String) {
        _title.update { name }
    }
    fun updateContent(name: String) {
        _content.update { name }
    }
    fun updateIsUpdate(isUpdate: Boolean) {
        _isUpdate.update { isUpdate }
    }

    fun updateSort() {
        when (_sort.value) {
            0 ->_sort.update { 1 }
            1 ->_sort.update { 0 }
        }
    }
    fun delete(noteEntity: NoteEntity){
        viewModelScope.launch {
            noteRepository.delete(noteEntity)
        }
    }

    val addNoteUiState = combine(
        _title, _content, _isUpdate
    ) { title, content, isUpdate ->
        AddNoteUiState(
            title = title,
            content = content,
            isUpdate = isUpdate
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = AddNoteUiState()
    )

    fun validate(): Boolean {
        return when {
            _title.value.isNotEmpty() || _content.value.isNotEmpty() -> true
            else -> false
        }
    }
    fun addNote() {
        viewModelScope.launch {
            if (_isUpdate.value){
                noteRepository.update(
                    NoteEntity(
                        id = _selectedItem.value.id,
                        title = _title.value,
                        content = _content.value
                    )
                )
            }
            else {
                noteRepository.addItem(
                    NoteEntity(
                        title = _title.value,
                        content = _content.value
                    )
                )
            }
            clear()
        }
    }
}

data class NoteState(
    val notes: List<NoteEntity> = emptyList()
)

data class AddNoteUiState(
    val title: String = "",
    val content: String = "",
    val isUpdate: Boolean = false
)
