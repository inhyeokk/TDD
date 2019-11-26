package com.rkddlsgur983.test.view.hello

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.rkddlsgur983.test.R
import com.rkddlsgur983.test.base.BaseActivity
import com.rkddlsgur983.test.databinding.ActivityHelloBinding

class HelloActivity: BaseActivity<ActivityHelloBinding>() {
    override val layoutRes: Int get() = R.layout.activity_hello
    private lateinit var helloViewModel: HelloViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hello)
    }

    override fun setupView() {
        helloViewModel = ViewModelProviders.of(this).get(HelloViewModel::class.java)
    }
}
