package com.rkddlsgur983.test.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel(application: Application): AndroidViewModel(application) {

    private val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    fun Disposable.register() {
        compositeDisposable.add(this)
    }

    fun registerDisposables(vararg  disposables: Disposable) {
        compositeDisposable.addAll(*disposables)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}