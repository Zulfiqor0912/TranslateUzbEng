package uz.gita.translateuzbeng.domain.repository

import uz.gita.translateuzbeng.data.source.local.pref.LocalStorage


class AppRepository {
    private val appDatabase = uz.gita.translateuzbeng.data.source.local.AppDatabase.instance()
    private val localStorage = LocalStorage.instance()

    companion object {
        private lateinit var repository: AppRepository

        fun instance(): AppRepository {
            if (!::repository.isInitialized) repository = AppRepository()
            return repository
        }
    }


    fun getAllContact() = appDatabase.getAllWords()

    fun engWords(query: String) = appDatabase.engWords(query)

    fun uzbWords(query: String) = appDatabase.uzbWords(query)

    fun updateWord(id: Int, isSave: Int) = appDatabase.updateWord(id, isSave)

    fun getAllSaveWords() = appDatabase.getAllSave()

    fun saveIsLan(bool: Boolean) = localStorage.saveIsLan(bool)

    fun getLan() = localStorage.getLan()
}