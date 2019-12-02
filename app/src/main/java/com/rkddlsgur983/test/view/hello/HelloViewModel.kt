package com.rkddlsgur983.test.view.hello

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.rkddlsgur983.test.base.BaseViewModel

class HelloViewModel(application: Application): BaseViewModel(application) {

    val hello = MutableLiveData<String>()

    init {
        hello.value = "click me!"
    }

    fun onHelloClick() {
        hello.value = "Hello world!"
    }
}