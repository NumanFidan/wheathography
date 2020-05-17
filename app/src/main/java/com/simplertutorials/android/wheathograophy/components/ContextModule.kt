package com.simplertutorials.android.wheathograophy.components

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule (context: Context){
    private var context: Context = context

    @Singleton
    @Provides
    fun getContext():Context{
        return this.context
    }
}