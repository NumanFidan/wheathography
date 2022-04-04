package com.simplertutorials.android.wheathograophy.components

import android.content.Context
import com.simplertutorials.android.wheathograophy.managers.ResourceManager
import dagger.Module
import dagger.Provides
import java.lang.ref.WeakReference
import javax.inject.Singleton

@Module
class ManagersModule {

    @Provides
    @Singleton
    fun provideResourceManager(context: Context): ResourceManager {
        return ResourceManager(WeakReference(context))
    }
}
