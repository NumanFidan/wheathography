package com.simplertutorials.android.wheathograophy

import android.app.Application
import com.simplertutorials.android.wheathograophy.koinComponents.apiModule
import com.simplertutorials.android.wheathograophy.koinComponents.appModule
import com.simplertutorials.android.wheathograophy.koinComponents.managersModule
import com.simplertutorials.android.wheathograophy.koinComponents.storageModule
import com.simplertutorials.android.wheathograophy.logging.DebugLogTree
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MainApplication)
            modules(appModule, storageModule, managersModule, apiModule)
        }
        Timber.plant(DebugLogTree())
    }
}
