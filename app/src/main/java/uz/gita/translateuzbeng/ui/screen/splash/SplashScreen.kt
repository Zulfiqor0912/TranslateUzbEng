package uz.gita.translateuzbeng.ui.screen.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import uz.gita.translateuzbeng.R
import uz.gita.translateuzbeng.ui.screen.parent.ParentScreen
import uz.gita.translateuzbeng.utils.addFragment

@SuppressLint("CustomSplashScreen")
class SplashScreen : Fragment(R.layout.screen_splash) {
    private val h = Handler(Looper.getMainLooper())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        h.postDelayed({
            addFragment(ParentScreen())
        }, 2500)
    }
}