package com.simplertutorials.android.wheathograophy.logging

import timber.log.Timber

class DebugLogTree : Timber.DebugTree() {
    protected override fun createStackElementTag(element: StackTraceElement): String? {
        // Add log statements line number to the log
        return super.createStackElementTag(element) + "(" + element.lineNumber + ")"
    }
}
