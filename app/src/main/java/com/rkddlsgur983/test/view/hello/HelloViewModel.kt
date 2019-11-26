package com.rkddlsgur983.test.view.hello

import androidx.lifecycle.MutableLiveData
import com.rkddlsgur983.test.base.BaseViewModel

class HelloViewModel: BaseViewModel() {

    val hello = MutableLiveData<String>()

    init {
        hello.value = "click me!"
    }

    fun onHelloClick() {
        hello.value = "Hello world!"
    }
}