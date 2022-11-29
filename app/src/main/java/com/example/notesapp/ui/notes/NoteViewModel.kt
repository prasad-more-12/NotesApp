package com.example.notesapp.ui.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.Note
import com.example.notesapp.data.NoteRepository
import com.example.notesapp.data.NotesDao
import com.example.notesapp.data.NotesDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class NoteViewModel(
    application: Application
) : AndroidViewModel(application) {
    // on below line we are creating a variable
    // for our all notes list and repository
    val allNotes: LiveData<List<Note>>
    val repository: NoteRepository

    // on below line we are initializing
    // our dao, repository and all notes
    init {
        val dao = NotesDatabase.getDatabase(application).getDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }

    // method for deleting a note
    // calling a delete method from repository to delete note.
    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    //method for updating a note.
    // calling a update method from repository to update note.
    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }


    // method for adding a new note to database
    //calling a method from repository to add a new note.
    fun addNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }

}