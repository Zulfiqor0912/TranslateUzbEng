package uz.gita.translateuzbeng.data.source.local

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor

class AppDatabase private constructor(context: Context) : uz.gita.translateuzbeng.data.source.local.DBHelper(context, "dictionary_uz.db", 1) {
    companion object {
        @SuppressLint("StaticFieldLeak")
        private lateinit var database: uz.gita.translateuzbeng.data.source.local.AppDatabase

        fun init(context: Context) {
            if (!uz.gita.translateuzbeng.data.source.local.AppDatabase.Companion::database.isInitialized) uz.gita.translateuzbeng.data.source.local.AppDatabase.Companion.database =
                uz.gita.translateuzbeng.data.source.local.AppDatabase(context)
        }

        fun instance() = uz.gita.translateuzbeng.data.source.local.AppDatabase.Companion.database
    }

    fun getAllWords(): Cursor {
        return database().rawQuery("SELECT * FROM dictionary", null)
    }

    fun engWords(query: String): Cursor {
        return database().rawQuery(
            "SELECT * FROM dictionary WHERE dictionary.english  LIKE '%$query%' ",
            null
        )
    }

    fun uzbWords(query: String): Cursor {
        return database().rawQuery(
            "SELECT * FROM dictionary WHERE dictionary.uzbek  LIKE '%$query%' ",
            null
        )
    }

    fun getAllSave(): Cursor {
        return database().rawQuery(
            "SELECT * FROM dictionary WHERE dictionary.favourite = 1",
            null
        )
    }

    fun updateWord(id: Int, isSave: Int) {
        val cv = ContentValues()
        cv.put("favourite", isSave)
        database().update("dictionary", cv, "dictionary.id = $id", null)
    }
}