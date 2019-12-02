package com.rkddlsgur983.test.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<VB: ViewDataBinding>: Fragment() {
    lateinit var binding: VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        onDataBinding()
        setupView()
        return binding.root
    }

    open fun onDataBinding() {
        binding.lifecycleOwner = this
    }

    open fun setupView() {

    }
}