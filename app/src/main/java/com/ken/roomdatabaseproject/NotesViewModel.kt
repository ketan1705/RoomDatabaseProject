package com.ken.roomdatabaseproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModels(private val repo: Repo) : ViewModel() {

    fun getAllNotes() = repo.getAllNotes()
    fun insertNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO)
        {
            repo.insert(note)
        }

    }

    fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.delete(note)
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.update(note)
        }
    }

}