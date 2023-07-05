package uz.gita.translateuzbeng.ui.screen.favourite

import android.database.Cursor

class FavouritePresenter(val view: FavouriteContract.View) : FavouriteContract.Presenter {
    private val model: FavouriteContract.Model = FavouriteModel()
    override fun getSaveWord(): Cursor = model.getAllSaveWords()
    override fun clickStar(id: Int, isSave: Int) {
        model.updateWord(id, isSave)
    }

    override fun getLan(): Boolean {
        return model.getLan()
    }


}