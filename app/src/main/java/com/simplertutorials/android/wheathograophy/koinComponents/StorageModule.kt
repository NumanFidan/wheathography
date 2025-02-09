package com.simplertutorials.android.wheathograophy.koinComponents

import android.content.Context
import android.content.SharedPreferences
import com.simplertutorials.android.wheathograophy.data.database.SharedPreferencesManager
import com.simplertutorials.android.wheathograophy.data.database.StorageRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val storageModule = module {
    single { StorageRepository(get(), KEY) }
    single { SharedPreferencesManager(get<SharedPreferences>().edit(), get()) }
    single { androidContext().getSharedPreferences(KEY, Context.MODE_PRIVATE) }
}

private const val KEY = "Cities"