package com.simplertutorials.android.wheathograophy.data.database

import android.content.SharedPreferences

class SharedPreferencesManager(private val editor: SharedPreferences.Editor,
                               private val settings: SharedPreferences) {

    fun writeSet(hashSet: HashSet<String>, key: String) {
        if (settings.contains(key)) {
            deleteValue(key)
        }
        editor.putStringSet(key, hashSet)
        editor.apply()
    }

    fun deleteValue(key: String) {
        editor.remove(key)
        editor.apply()
    }

    fun readSet(key: String): MutableSet<String>? {
        return if (settings.contains(key))
            settings.getStringSet(key, null)
        else
            null
    }

    fun writeStringValue(key: String, value: String) {
        if (settings.contains(key))
            return
        editor.putString(key, value)
        editor.apply()
    }

    fun readStringValue(key: String): String? {
        return settings.getString(key, null)
    }
}
