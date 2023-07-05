package uz.gita.translateuzbeng.ui.screen.favourite

import android.database.Cursor

interface FavouriteContract {
    interface Model {
        fun getAllSaveWords(): Cursor
        fun updateWord(id: Int, isSave: Int)
        fun getLan(): Boolean
    }

    interface View {

    }

    interface Presenter {
        fun getSaveWord(): Cursor
        fun clickStar(id:Int,isSave: Int)
        fun getLan(): Boolean
    }

}