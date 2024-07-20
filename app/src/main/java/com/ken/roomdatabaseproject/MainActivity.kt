package com.ken.roomdatabaseproject

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), NoteAdapter.ClickListener {
    private lateinit var noteViewModel: NotesViewModels
    private lateinit var repo: Repo
    private lateinit var noteDatabase: NoteDatabase
    private lateinit var factory: NotesViewModelFactory
    private lateinit var rv: RecyclerView
    private lateinit var noteAdapter: NoteAdapter
    private lateinit var addNote: FloatingActionButton
    private lateinit var dialog: Dialog
    private lateinit var title: EditText
    private lateinit var content: EditText
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        init()



        noteViewModel.getAllNotes().observe(this) {
            noteAdapter = NoteAdapter(
                this, it, this
            )
            rv.adapter = noteAdapter
            Log.d("*****", "Notes Data: $it")
        }

        addNote.setOnClickListener {
            openDialog(true)
        }

    }

    private fun openDialog(comingFromFAB: Boolean) {
        dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.custom_dialog_layout)

        title = dialog.findViewById(R.id.name)
        content = dialog.findViewById(R.id.content)
        btnSave = dialog.findViewById(R.id.btnSave)

        btnSave.setOnClickListener {
            val note = Note(title = title.text.toString(), content = content.text.toString())

            if (comingFromFAB)
                noteViewModel.insertNote(note)
            else
                noteViewModel.updateNote(note)

            dialog.dismiss()
        }
        dialog.show()

//        dialog.show()

    }

    fun init() {
        noteDatabase = NoteDatabase(this)
        repo = Repo(noteDatabase.noteDao())
        factory = NotesViewModelFactory(repo)
        noteViewModel = ViewModelProvider(this, factory).get(NotesViewModels::class.java)
        rv = findViewById(R.id.recyclerView)
        addNote = findViewById(R.id.addNote)

    }

    override fun updateItem(note: Note) {

        // we can write the logic of updating Note note
        openDialog(false)
    }

    override fun deleteItem(note: Note) {
        // we can write the logic of deleting Note
        noteViewModel.deleteNote(note)

    }
}