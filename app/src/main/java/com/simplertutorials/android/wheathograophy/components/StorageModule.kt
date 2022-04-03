package com.simplertutorials.android.wheathograophy.components

import android.content.Context
import android.content.SharedPreferences
import com.simplertutorials.android.wheathograophy.data.database.SharedPreferencesManager
import com.simplertutorials.android.wheathograophy.data.database.StorageRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {

    @Singleton
    @Provides
    fun provideStoragePreferences(sharedPreferencesManager: SharedPreferencesManager): StorageRepository {
        return StorageRepository(sharedPreferencesManager, KEY)
    }

    @Singleton
    @Provides
    fun provideSharedPreferencesManager(sharedPreferences: SharedPreferences): SharedPreferencesManager {
        return SharedPreferencesManager(sharedPreferences.edit(), sharedPreferences)
    }

    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(KEY, Context.MODE_PRIVATE)
    }

    companion object {
        private const val KEY = "Cities"
    }
}
