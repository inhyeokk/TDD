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
import com.rkddlsgur983.test.view.login.data.ApplicationDelegateImpl
import com.rkddlsgur983.test.view.login.entity.ViewType

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val loginViewModel = LoginViewModel(ApplicationDelegateImpl(activity!!.application))

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

            showMessageEvent.observe(owner, Observer { message ->
                BasicUtils.showToast(owner.context, message)
            })

            moveViewEvent.observe(owner, Observer { viewType ->
                when (viewType) {
                    ViewType.LOGIN -> BasicUtils.showToast(owner.context, getString(R.string.login_toast_login_click))
                    ViewType.JOIN -> BasicUtils.showToast(owner.context, getString(R.string.login_toast_join_click))
                    else -> {}
                }
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
