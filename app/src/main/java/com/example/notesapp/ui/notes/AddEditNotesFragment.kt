package com.example.notesapp.ui.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notesapp.R
import com.example.notesapp.data.Note
import com.example.notesapp.databinding.FragmentAddEditNotesBinding
import com.example.notesapp.databinding.FragmentNotesBinding
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class AddEditNotesFragment : Fragment() {

    private lateinit var viewModel: NoteViewModel
    private var _binding: FragmentAddEditNotesBinding? = null
    private val binding get() = _binding!!

    val args: AddEditNotesFragmentArgs by navArgs()
    var noteID = -1;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddEditNotesBinding.inflate(inflater, container, false)


        val application = requireNotNull(this.activity).application
        val viewModelFactory = NotesViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(NoteViewModel::class.java)
        val isEdit = args.toEdit
        val editNote = args.noteSelected

        if (isEdit) {

            val noteTitle = editNote?.noteTitle
            val noteDescription = editNote?.noteDesc
            noteID = editNote!!.noteId
            binding.idEdtNoteName.setText(noteTitle)
            binding.idEdtNoteDesc.setText(noteDescription)
            binding.idBtn.setText("Update Now")

        } else {
            binding.idBtn.setText("Save Note")
        }
        binding.idBtn.setOnClickListener {
            // on below line we are getting
            // title and desc from edit text.

            // on below line we are checking the type
            // and then saving or updating the data.
            if (isEdit) {

                val noteTitle = binding.idEdtNoteName.text.toString()
                val noteDescription = binding.idEdtNoteDesc.text.toString()
                if (noteTitle!!.isNotEmpty() && noteDescription!!.isNotEmpty()) {
                    val created: Long = System.currentTimeMillis()
                    val currentDateAndTime: String =
                        DateFormat.getDateTimeInstance().format(created)
                    val updatedNote = Note(noteTitle, noteDescription, currentDateAndTime)
                    updatedNote.noteId=noteID
                    viewModel.updateNote(updatedNote)
                    Toast.makeText(context, "$noteTitle Updated....", Toast.LENGTH_LONG).show()

                }
            } else {
                val noteTitle = binding.idEdtNoteName.text.toString()
                val noteDescription = binding.idEdtNoteDesc.text.toString()
//                if (noteTitle!!.isNotEmpty() && noteDescription!!.isNotEmpty()) {
                val created: Long = System.currentTimeMillis()
                val currentDateAndTime: String =
                    DateFormat.getDateTimeInstance().format(created)

                viewModel.addNote(Note(noteTitle, noteDescription, currentDateAndTime))
                Toast.makeText(context, "$noteTitle Added", Toast.LENGTH_LONG).show()
                //  }
            }

            findNavController().navigate(R.id.action_addEditNotesFragment_to_notesFragment)
        }

        return binding.root
    }


}