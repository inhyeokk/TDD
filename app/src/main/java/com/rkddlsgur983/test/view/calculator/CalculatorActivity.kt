package com.rkddlsgur983.test.view.calculator

import com.rkddlsgur983.test.R
import com.rkddlsgur983.test.base.BaseActivity
import com.rkddlsgur983.test.databinding.ActivityMapBinding

class CalculatorActivity : BaseActivity<ActivityMapBinding>() {
    override val layoutRes = R.layout.activity_calculator
    private val calculatorViewModel = CalculatorViewModel()

    override fun onDataBinding() {
        binding.vm = calculatorViewModel
        super.onDataBinding()
    }
}
