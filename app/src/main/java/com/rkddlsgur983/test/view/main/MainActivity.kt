package com.rkddlsgur983.test.view.main

import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rkddlsgur983.test.R
import com.rkddlsgur983.test.base.BaseActivity
import com.rkddlsgur983.test.databinding.ActivityMainBinding
import com.rkddlsgur983.test.view.main.adapter.KakaoWebAdapter
import com.rkddlsgur983.test.view.main.data.MainRepositoryImpl

class MainActivity: BaseActivity<ActivityMainBinding>() {

    override val layoutRes = R.layout.activity_main
    private val mainViewModel = MainViewModel(MainRepositoryImpl())

    override fun onDataBinding() {
        // mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.vm = mainViewModel
        super.onDataBinding()
    }

    override fun setupView() {
        super.setupView()
        initEdSearch()
        initRecyclerView()
    }

    private fun initEdSearch() {
        binding.edSearch.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    mainViewModel.requestKakaoWeb(binding.edSearch.text.toString())
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun initRecyclerView() {

        val linearLayoutManager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(
            binding.recyclerView.context,
            linearLayoutManager.orientation
        )
        val kakaoWebAdapter = KakaoWebAdapter()
        binding.recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = kakaoWebAdapter
            addItemDecoration(decoration)
        }

        mainViewModel.observeKakaoWebItem().observe(this, Observer {
            kakaoWebAdapter.addAll(it)
        })
    }
}
