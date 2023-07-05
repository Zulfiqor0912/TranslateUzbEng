package uz.gita.translateuzbeng.ui.screen.home

import android.database.Cursor

class HomePresenter(val view: HomeContract.View) : HomeContract.Presenter {
    private val model: HomeContract.Model = HomeModel()

    override fun loadData(): Cursor = model.getAllWords()
    override fun engWords(query: String): Cursor {
        return model.engWords(query)
    }

    override fun uzbekWords(query: String): Cursor {
        return model.uzbekWords(query)
    }

    override fun clickStar(isSave: Int, id: Int) {
        model.updateWord(isSave, id)
    }

    override fun load() {
        view.loadView(model.getLan())
    }

    override fun clickLanButton(bool: Boolean) {
        model.saveIsLan(bool)
        view.loadView(model.getLan())
    }

}