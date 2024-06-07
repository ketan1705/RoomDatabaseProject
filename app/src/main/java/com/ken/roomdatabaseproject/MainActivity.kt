package com.ken.roomdatabaseproject

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var noteViewModel: NotesViewModels
    private lateinit var repo: Repo
    private lateinit var noteDatabase: NoteDatabase
    private lateinit var factory: NotesViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        noteViewModel.insertNote(
            Note(
                title = "Note 1",
                content = "This is note 1"
            )
        )
        noteViewModel.insertNote(
            Note(
                title = "Note 2",
                content = "This is note 2"
            )
        )
        noteViewModel.getAllNotes().observe(this)
        {
            Log.d("*****", "Notes Data: $it")
        }
    }

    fun init() {
        noteDatabase = NoteDatabase(this)
        repo = Repo(noteDatabase.noteDao())
        factory = NotesViewModelFactory(repo)
        noteViewModel = ViewModelProvider(this, factory).get(NotesViewModels::class.java)

    }
}