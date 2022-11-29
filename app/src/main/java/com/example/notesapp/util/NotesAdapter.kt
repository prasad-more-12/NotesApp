package com.example.notesapp.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.data.Note
import com.example.notesapp.databinding.FragmentNotesBinding
import com.example.notesapp.databinding.NoteItemsBinding

class NotesAdapter(

    private val noteClickDeleteInterface: NoteClickDeleteInterface,
    private val noteClickInterface: NoteClickInterface
) :
    RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {
    // on below line we are creating a
    // variable for our all notes list.
    private val allNotes = ArrayList<Note>()

    class NoteViewHolder(val binding: NoteItemsBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            NoteItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.binding.noteTitleTV.setText(allNotes.get(position).noteTitle)
        holder.binding.noteDateTV.setText(allNotes.get(position).noteTime)
        holder.binding.noteDeleteIV.setOnClickListener {
            noteClickDeleteInterface.onDeleteIconClick(allNotes.get(position))
        }
        holder.itemView.setOnClickListener {

            noteClickInterface.onNoteClick(allNotes.get(position))
        }
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    // below method is use to update list of notes.
    fun updateList(newList: List<Note>) {
        //clearing our notes array list
        allNotes.clear()
        // adding a new list to our all notes list.
        allNotes.addAll(newList)
        // calling notify data change method to notify our adapter.
        notifyDataSetChanged()
    }

    interface NoteClickDeleteInterface {
        // creating a method for click action on delete image view.
        fun onDeleteIconClick(note: Note)
    }

    interface NoteClickInterface {
        // creating a method for click action on recycler view item for updating it.
        fun onNoteClick(note: Note)
    }
}