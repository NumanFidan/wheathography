package com.simplertutorials.android.wheathograophy.data.database

import android.content.SharedPreferences

class DatabaseManager(private val editor: SharedPreferences.Editor,
                      private val settings: SharedPreferences) {

    fun writeSet(hashSet: HashSet<String>, key: String) {
        if (settings.contains(key)) {
            deleteValue(key)
        }
        editor.putStringSet(key, hashSet)
        editor.apply()
    }

    fun deleteValue(key: String){
        editor.remove(key)
        editor.apply()
    }

    fun readSet(key: String): MutableSet<String>? {
        return if (settings.contains(key))
            settings.getStringSet(key,null)
        else
            null
    }
}