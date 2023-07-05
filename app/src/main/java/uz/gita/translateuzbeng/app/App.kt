package uz.gita.translateuzbeng.app

import android.app.Application
import android.content.Context
import uz.gita.translateuzbeng.data.source.local.pref.LocalStorage

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        uz.gita.translateuzbeng.data.source.local.AppDatabase.init(this)
        LocalStorage.init(this)
    }

    companion object {
        lateinit var instance: Context
    }
}