package com.hafizcode.noteappdicoding.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.concurrent.Executors

@Database(entities = [Note::class], version = 1)
abstract class NoteRoomDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: NoteRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): NoteRoomDatabase {
            if (INSTANCE == null) {
                synchronized(NoteRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        NoteRoomDatabase::class.java,
                        "note_database"
                    ).addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            addDummy()
                        }
                    })
                        .build()
                }
            }
            return INSTANCE as NoteRoomDatabase
        }

        private fun addDummy() {
            Executors.newSingleThreadExecutor().execute {
                val list: MutableList<Note> = ArrayList()
                for (i in 0..9) {
                    val dummyNote = Note()
                    dummyNote.title = "Tugas $i"
                    dummyNote.description = "Belajar Modul $i"
                    dummyNote.date = "2021/01/01 09:09:09:0$i"
                    list.add(dummyNote)
                }
                INSTANCE?.noteDao()?.insertAll(list)
            }
        }
    }
}