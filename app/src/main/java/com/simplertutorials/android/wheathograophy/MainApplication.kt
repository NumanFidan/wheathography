package com.simplertutorials.android.wheathograophy

import android.app.Application
import com.simplertutorials.android.wheathograophy.components.ApplicationComponent
import com.simplertutorials.android.wheathograophy.components.ContextModule
import com.simplertutorials.android.wheathograophy.components.DaggerApplicationComponent

class MainApplication : Application() {
    private var applicationComponent: ApplicationComponent? = null
    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder()
            .contextModule(ContextModule(this))
            .build()
    }

    val component: ApplicationComponent?
        get() = applicationComponent
}
