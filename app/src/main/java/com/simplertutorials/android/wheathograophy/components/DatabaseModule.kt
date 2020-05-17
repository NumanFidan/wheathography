package com.simplertutorials.android.wheathograophy.components

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DatabaseModule {

    private var PREFS_NAME:String = "weatherogrophy"


    @Singleton
    @Provides
    fun provideEditor(settings: SharedPreferences): SharedPreferences.Editor? {
        return settings.edit()
    }

    @Singleton
    @Provides
    fun provideSharePreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, 0)
    }
}