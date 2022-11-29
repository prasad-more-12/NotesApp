package com.example.notesapp.data

import androidx.lifecycle.LiveData

class NoteRepository(private val notesDao: NotesDao) {

    //  variable for list and we are getting all the notes from our DAO class.
    val allNotes: LiveData<List<Note>> = notesDao.getAllNotes()

    //for adding the note to our database.
    suspend fun insert(note: Note) {
        notesDao.insert(note)
    }

    // for deleting note from database.
    suspend fun delete(note: Note){
        notesDao.delete(note)
    }

    //for updating our from database.
    suspend fun update(note: Note){
        notesDao.update(note)
    }
}