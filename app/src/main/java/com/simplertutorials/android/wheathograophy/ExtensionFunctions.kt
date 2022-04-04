package com.simplertutorials.android.wheathograophy

import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer

fun <T> Observable<T>.subscribe(
    onNext: Consumer<T>,
    onError: Consumer<Throwable>? = null,
    onComplete: Action? = null
): Disposable {
    return when {
        onError == null -> subscribe(onNext)
        onComplete == null -> subscribe(onNext, onError)
        else -> subscribe(onNext, onError, onComplete)
    }
}
