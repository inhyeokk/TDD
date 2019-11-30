package com.rkddlsgur983.test.view.login

import com.rkddlsgur983.test.R
import com.rkddlsgur983.test.base.BaseActivity
import com.rkddlsgur983.test.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    override val layoutRes = R.layout.activity_login

    override fun setupView() {
        initLoginFragment()
    }

    private fun initLoginFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(binding.fragmentContainer.id, LoginFragment())
            .commit()
    }
}

