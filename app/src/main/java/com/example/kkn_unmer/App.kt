package com.example.kkn_unmer

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.chibatching.kotpref.Kotpref

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        Kotpref.init(this)
        //JodaTimeAndroid.init(this)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

}