package uz.gita.translateuzbeng.ui.screen.home

import android.database.Cursor

interface HomeContract {

    interface Model {

        fun getAllWords(): Cursor

        fun engWords(query: String): Cursor

        fun uzbekWords(query: String): Cursor

        fun updateWord(isSave: Int, id: Int)

        fun saveIsLan(bool: Boolean)

        fun getLan(): Boolean

    }

    interface View {

        fun loadView(bool: Boolean)

    }

    interface Presenter {

        fun loadData(): Cursor

        fun engWords(query: String): Cursor

        fun uzbekWords(query: String): Cursor

        fun clickStar(isSave: Int, id: Int)

        fun load()

        fun clickLanButton(bool: Boolean)
    }


}