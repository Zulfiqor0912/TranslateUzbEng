package uz.gita.translateuzbeng.ui.screen.home

import android.database.Cursor
import uz.gita.translateuzbeng.domain.repository.AppRepository

class HomeModel : HomeContract.Model {
    private val repository = AppRepository.instance()
    override fun getAllWords(): Cursor {
        return repository.getAllContact()
    }

    override fun engWords(query: String): Cursor {
        return repository.engWords(query)
    }

    override fun uzbekWords(query: String): Cursor {
        return repository.uzbWords(query)
    }

    override fun updateWord(isSave: Int, id: Int) {
        repository.updateWord(isSave, id)
    }

    override fun saveIsLan(bool: Boolean) {
        repository.saveIsLan(bool)
    }

    override fun getLan(): Boolean {
        return repository.getLan()
    }
}