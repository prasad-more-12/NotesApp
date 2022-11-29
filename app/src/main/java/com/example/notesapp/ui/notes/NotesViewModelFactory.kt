package com.example.notesapp.ui.notes

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.data.NotesDao


class NotesViewModelFactory (private val application: Application): ViewModelProvider.Factory{
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
           return NoteViewModel(application) as T
      }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}