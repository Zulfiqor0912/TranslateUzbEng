package uz.gita.translateuzbeng.ui.adapters

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.gita.translateuzbeng.ui.screen.favourite.FavouriteScreen
import uz.gita.translateuzbeng.ui.screen.home.HomeScreen
import uz.gita.translateuzbeng.ui.screen.settings.SettingsScreen


class ScreensAdapter(fm: FragmentManager, lf: Lifecycle) : FragmentStateAdapter(fm, lf) {
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> HomeScreen().apply {
                arguments = bundleOf(Pair("POS", position))
            }
            1 -> FavouriteScreen().apply {
                arguments = bundleOf(Pair("POS", position))
            }
            else -> SettingsScreen().apply {
                arguments = bundleOf(Pair("POS", position))
            }
        }
    }
}