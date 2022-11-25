package com.example.room.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.room.database.Note
import com.example.room.database.NoteRepository

class MainViewModel(application: Application) : ViewModel() {
    private val mNoteRepository : NoteRepository = NoteRepository(application)

    fun getAllNotes() : LiveData<List<Note>> = mNoteRepository.getAllNotes()
}