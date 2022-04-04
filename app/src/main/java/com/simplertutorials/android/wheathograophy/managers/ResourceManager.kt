package com.simplertutorials.android.wheathograophy.managers

import android.content.Context
import androidx.annotation.StringRes
import java.lang.ref.WeakReference

class ResourceManager(private val context: WeakReference<Context>) {
    fun getString(@StringRes resId: Int): String {
        return context.get()!!.getString(resId)
    }
    fun getString(@StringRes resId: Int, vararg formatArgs: Any): String {
        return context.get()!!.getString(resId, *formatArgs)
    }
}
