package com.simplertutorials.android.wheathograophy.components

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.Nullable
import com.simplertutorials.android.wheathograophy.data.database.DatabaseRepository
import com.simplertutorials.android.wheathograophy.data.database.DatabaseManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DatabaseModule {

    private var PREFS_NAME: String = "weatherogrophy"


    @Singleton
    @Provides
    fun provideDatabaseRepository(databaseManager: DatabaseManager): DatabaseRepository {
        return DatabaseRepository(databaseManager)
    }

    @Singleton
    @Provides
    fun provideDatabaseManager(settings: SharedPreferences): DatabaseManager {

        return DatabaseManager(settings.edit(), settings)
    }

    @Singleton
    @Provides
    fun provideSharePreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, 0)
    }

    @Singleton
    @Provides
    fun provideEditor(settings: SharedPreferences): SharedPreferences.Editor? {
        return settings.edit()
    }

}