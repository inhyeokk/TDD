package com.rkddlsgur983.test.view.main

import android.content.Intent
import android.net.Uri
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
    private lateinit var kakaoWebAdapter: KakaoWebAdapter

    override fun onDataBinding() {
        // mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.vm = mainViewModel
        super.onDataBinding()
    }

    override fun setupView() {
        super.setupView()
        initEdSearch()
        initRecyclerView()
        observeMainViewModel()
    }

    private fun initEdSearch() {

        binding.edSearch.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    mainViewModel.requestKakaoWeb(binding.edSearch.text.toString(), 10)
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
        kakaoWebAdapter = KakaoWebAdapter(mainViewModel)
        binding.recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = kakaoWebAdapter
            addItemDecoration(decoration)
        }
    }

    private fun observeMainViewModel() {

        val owner = this
        with(mainViewModel) {
            kakaoWebItemLiveData.observe(owner, Observer { kakaoWebItemList ->
                kakaoWebAdapter.addAll(kakaoWebItemList)
            })

            moveToExternalBrowserEvent.observe(owner, Observer { kakaoWebItem ->
                moveToExternalBrowser(kakaoWebItem.url)
            })
        }
    }

    private fun moveToExternalBrowser(url: String) {

        if (url.compareTo("") != 0) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }
}
