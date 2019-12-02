package com.rkddlsgur983.test.view.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import com.rkddlsgur983.test.R

import com.rkddlsgur983.test.databinding.FragmentLoginBinding
import com.rkddlsgur983.test.util.BasicUtils
import com.rkddlsgur983.test.view.login.entity.LoginType

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val loginViewModel = LoginViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(
            LayoutInflater.from(container!!.context), container, false
        )
        onDataBinding()
        setupView()
        return binding.root
    }

    private fun onDataBinding() {
        binding.lifecycleOwner = this
        binding.vm = loginViewModel

        val owner = this
        with(loginViewModel) {
            loginClickable.observe(owner, Observer {
                binding.btnLogin.apply {
                    isClickable = it
                    isSelected = it
                }
            })

            loginClickEvent.observe(owner, Observer { loginType ->
                when (loginType) {
                    LoginType.VALID -> BasicUtils.showToast(owner.context, getString(R.string.login_toast_login_click))
                    LoginType.INVALID_EMAIL -> BasicUtils.showToast(owner.context, getString(R.string.login_toast_invalid_email))
                    LoginType.INVALID_PASSWORD -> BasicUtils.showToast(owner.context, getString(R.string.login_toast_invalid_password))
                    LoginType.NONE_EMAIL -> BasicUtils.showToast(owner.context, getString(R.string.login_toast_none_email))
                    LoginType.NONE_PASSWORD -> BasicUtils.showToast(owner.context, getString(R.string.login_toast_none_password))
                    else -> {}
                }
            })

            joinClickEvent.observe(owner, Observer {
                BasicUtils.showToast(owner.context, getString(R.string.login_toast_join_click))
            })
        }
    }

    private fun setupView() {
        with(binding) {
            edId.doOnTextChanged { text, _, _, _ ->
                val value = text.toString()
                loginViewModel.onUpdateId(value)
            }
            edPw.doOnTextChanged { text, _, _, _ ->
                val value = text.toString()
                loginViewModel.onUpdatePassword(value)
            }
        }
    }
}
