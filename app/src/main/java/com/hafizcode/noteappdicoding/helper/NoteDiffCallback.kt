package com.hafizcode.noteappdicoding.helper

import androidx.recyclerview.widget.DiffUtil
import com.hafizcode.noteappdicoding.database.Note

class NoteDiffCallback(private val mOldNoteList: List<Note>, private val mNewNoteList: List<Note>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldNoteList.size
    }

    override fun getNewListSize(): Int {
        return mNewNoteList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldNoteList[oldItemPosition].id == mNewNoteList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldData = mOldNoteList[oldItemPosition]
        val newData = mNewNoteList[newItemPosition]
        return oldData.title == newData.title && oldData.description == newData.description
    }
}