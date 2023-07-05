package uz.gita.translateuzbeng.data.source.local.pref

import android.content.Context
import android.content.SharedPreferences

class LocalStorage private constructor(context: Context) {
    private var pref: SharedPreferences
    private var editor: SharedPreferences.Editor

    init {
        pref = context.getSharedPreferences("DICTIONARY", Context.MODE_PRIVATE)
        editor = pref.edit()
    }

    companion object {
        private lateinit var localStorage: LocalStorage

        fun init(context: Context) {
            if (!::localStorage.isInitialized) localStorage = LocalStorage(context)
        }

        fun instance(): LocalStorage = localStorage
    }

    fun saveIsLan(bool: Boolean) {
        editor.putBoolean("IsLan", bool)
        editor.apply()
    }

    fun getLan() = pref.getBoolean("IsLan", false)
}