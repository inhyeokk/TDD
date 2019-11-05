package com.rkddlsgur983.test.view.main

import androidx.lifecycle.MutableLiveData
import com.rkddlsgur983.test.base.BaseViewModel

class MainViewModel: BaseViewModel() {

    private val text = MutableLiveData<String>()

    init {
        text.postValue("click me!")
    }

    fun observeText() = text

    fun setText() {
        text.postValue("Hello world!")
    }
}