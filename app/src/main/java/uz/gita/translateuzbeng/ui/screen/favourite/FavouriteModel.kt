package uz.gita.translateuzbeng.ui.screen.favourite

import android.database.Cursor
import uz.gita.translateuzbeng.domain.repository.AppRepository

class FavouriteModel : FavouriteContract.Model {
    private val repository = AppRepository.instance()

    override fun getAllSaveWords(): Cursor  = repository.getAllSaveWords()
    override fun updateWord(id: Int, isSave: Int) {
        repository.updateWord(id,isSave)
    }

    override fun getLan(): Boolean {
        return repository.getLan()
    }


}