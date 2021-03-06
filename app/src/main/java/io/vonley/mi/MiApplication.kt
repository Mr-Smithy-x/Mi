package io.vonley.mi

import android.app.Application
import android.content.Intent
import dagger.hilt.android.HiltAndroidApp
import io.vonley.mi.intents.PSXService

@HiltAndroidApp
class MiApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startService(Intent(this, PSXService::class.java))
    }

}