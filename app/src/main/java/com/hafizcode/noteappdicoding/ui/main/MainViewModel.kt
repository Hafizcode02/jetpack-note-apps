package com.hafizcode.noteappdicoding.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.hafizcode.noteappdicoding.database.Note
import com.hafizcode.noteappdicoding.repository.NoteRepository

class MainViewModel(application: Application) : ViewModel() {
    private val mNoteRepository: NoteRepository = NoteRepository(application)

    fun getAllNotes(): LiveData<PagedList<Note>> =
        LivePagedListBuilder(mNoteRepository.getAllNotes(), 10).build()
}