package uz.gita.translateuzbeng.ui.screen.parent

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.translateuzbeng.R
import uz.gita.translateuzbeng.databinding.ScreenParentBinding

import uz.gita.translateuzbeng.ui.adapters.ScreensAdapter
import uz.gita.translateuzbeng.ui.screen.favourite.FavouriteScreen
import uz.gita.translateuzbeng.ui.screen.home.HomeScreen
import uz.gita.translateuzbeng.ui.screen.settings.SettingsScreen


class ParentScreen : Fragment(R.layout.screen_parent) {
    private val binding by viewBinding(ScreenParentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ScreensAdapter(childFragmentManager, lifecycle)


        binding.apply {
            viewPager.adapter = adapter


            navBar.setOnItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.homeScreen -> {
                        childFragmentManager
                            .beginTransaction()
                            .replace(R.id.parent, HomeScreen())
                            .commit()
                        true
                    }

                    R.id.saveScreen -> {
                        childFragmentManager
                            .beginTransaction()
                            .replace(R.id.parent, FavouriteScreen())
                            .commit()
                        true
                    }

                    else -> {
                        childFragmentManager
                            .beginTransaction()
                            .replace(R.id.parent, SettingsScreen())
                            .commit()
                        true
                    }
                }
            }
            viewPager.registerOnPageChangeCallback(callback)
        }
    }
        private val callback: ViewPager2.OnPageChangeCallback =
        object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> {
                        binding.navBar.selectedItemId = R.id.homeScreen
                    }

                    1 -> binding.navBar.selectedItemId = R.id.saveScreen
                    else -> binding.navBar.selectedItemId = R.id.infoScreen
                }
            }
        }
}
