package uz.gita.translateuzbeng.ui.screen.favourite

import android.database.Cursor
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.translateuzbeng.R
import uz.gita.translateuzbeng.databinding.ScreenFavouriteBinding
import uz.gita.translateuzbeng.ui.adapters.FavouriteAdapter


class FavouriteScreen : Fragment(R.layout.screen_favourite), FavouriteContract.View {
    private val binding by viewBinding(ScreenFavouriteBinding::bind)
    private lateinit var adapter: FavouriteAdapter
    private lateinit var presenter: FavouritePresenter
    private lateinit var cursor: Cursor
    private var bool = false


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = FavouritePresenter(this)
        cursor = presenter.getSaveWord()

        bool = presenter.getLan()
        adapter = FavouriteAdapter(bool, cursor)

        binding.apply {
            recyclerFavourite.adapter = adapter
            recyclerFavourite.layoutManager = LinearLayoutManager(requireContext())
            adapter.setClickStarBtn { id, isSave ->
                presenter.clickStar(id, isSave)
                adapter.cursor = presenter.getSaveWord()
                adapter.notifyDataSetChanged()
            }
            if (cursor.count == 0) placeholder.visibility = View.VISIBLE
            else placeholder.visibility = View.INVISIBLE

        }
    }
}