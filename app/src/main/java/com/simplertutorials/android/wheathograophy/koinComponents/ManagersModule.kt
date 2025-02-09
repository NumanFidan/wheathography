package com.simplertutorials.android.wheathograophy.koinComponents

import com.simplertutorials.android.wheathograophy.managers.ResourceManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import java.lang.ref.WeakReference

val managersModule = module {
    single { ResourceManager(WeakReference(androidContext())) }
}
