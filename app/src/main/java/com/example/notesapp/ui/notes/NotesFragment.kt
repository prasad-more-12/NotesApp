package com.example.notesapp.ui.notes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.data.Note
import com.example.notesapp.databinding.FragmentNotesBinding
import com.example.notesapp.util.NotesAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NotesFragment : Fragment(), NotesAdapter.NoteClickInterface,
    NotesAdapter.NoteClickDeleteInterface {
    private lateinit var viewModel: NoteViewModel
    private var _binding:FragmentNotesBinding ? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotesBinding.inflate(inflater,container,false)

        // on below line we are setting layout
        // manager to our recycler view.
        binding.notesRV.layoutManager = LinearLayoutManager(context)

        // on below line we are initializing our adapter class.
        val noteRVAdapter = NotesAdapter(this, this)

        // on below line we are setting
        // adapter to our recycler view.
        binding.notesRV.adapter = noteRVAdapter

        val application = requireNotNull(this.activity).application
        val viewModelFactory = NotesViewModelFactory(application)
        viewModel = ViewModelProvider(this,viewModelFactory).get(NoteViewModel::class.java)

        // on below line we are calling all notes method
        // from our view modal class to observer the changes on list.
        viewModel.allNotes.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                // on below line we are updating our list.
                noteRVAdapter.updateList(it)
            }
        })
        binding.idFAB.setOnClickListener {
            // adding a click listener for fab button
            // and opening a new intent to add a new note.
            Toast.makeText(context,"fab triggered",Toast.LENGTH_SHORT).show()
           findNavController().navigate(R.id.action_notesFragment_to_addEditNotesFragment)
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onDeleteIconClick(note: Note) {
        // in on note click method we are calling delete
        // method from our view modal to delete our not.
        viewModel.deleteNote(note)
        // displaying a toast message
        Toast.makeText(context, "${note.noteTitle} Deleted", Toast.LENGTH_LONG).show()
    }

    override fun onNoteClick(note: Note) {

        val action=NotesFragmentDirections.actionNotesFragmentToAddEditNotesFragment(note?:null,true)
        findNavController().navigate(action)
    }


}