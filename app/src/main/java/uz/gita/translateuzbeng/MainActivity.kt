package uz.gita.translateuzbeng

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.gita.translateuzbeng.ui.screen.splash.SplashScreen
import uz.gita.translateuzbeng.utils.addFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        setSupportActionBar(toolbar)
        addFragment(SplashScreen())
    }
}