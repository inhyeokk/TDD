package com.rkddlsgur983.test.view.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rkddlsgur983.test.R
import com.rkddlsgur983.test.base.BaseActivity
import com.rkddlsgur983.test.databinding.ActivityMainBinding

class MainActivity: BaseActivity<ActivityMainBinding>() {

    override val layoutRes = R.layout.activity_main
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
    }

    override fun setupView() {

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mainViewModel.observeText().observe(this, Observer {
            binding.btnMain.text = it
        })
    }

    fun onButtonClick(v: View) {
        mainViewModel.setText()
    }
}
