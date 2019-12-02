package com.rkddlsgur983.test.view.calculator

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.rkddlsgur983.test.base.BaseViewModel
import com.rkddlsgur983.test.view.calculator.entity.OperatorType

class CalculatorViewModel(application: Application): BaseViewModel(application) {

    val firstText = MutableLiveData<String>()
    val secondText = MutableLiveData<String>()
    val operatorText = MutableLiveData<String>()
    val operatorClickEvent = MutableLiveData<OperatorType>()

    init {
        firstText.value = "3"
        secondText.value = "7"
        operatorText.value = "+"
    }

    private fun getFirst() = firstText.value!!.toInt()
    private fun getSecond() = secondText.value!!.toInt()

    fun onOperatorClick(operatorType: OperatorType) {
        operatorClickEvent.value = operatorType
    }

    val resultText: LiveData<String> = Transformations.map(operatorClickEvent) { operatorType ->
        operatorText.value = operatorType.type
        when (operatorType) {
            OperatorType.PLUS -> (getFirst() + getSecond()).toString()
            OperatorType.MINUS -> (getFirst() - getSecond()).toString()
            OperatorType.MULTIPLE ->(getFirst() * getSecond()).toString()
            OperatorType.DIVIDE -> (getFirst() / getSecond()).toString()
            else -> {""}
        }
    }
}