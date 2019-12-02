package com.rkddlsgur983.test.view.kakao.web

import android.content.Intent
import android.net.Uri
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rkddlsgur983.test.R
import com.rkddlsgur983.test.base.BaseActivity
import com.rkddlsgur983.test.databinding.ActivityKakaoWebBinding
import com.rkddlsgur983.test.view.kakao.web.adapter.KakaoWebAdapter
import com.rkddlsgur983.test.view.kakao.web.data.KakaoWebRepositoryImpl

class KakaoWebActivity: BaseActivity<ActivityKakaoWebBinding>() {

    override val layoutRes = R.layout.activity_kakao_web
    private val kakaoWebViewModel = KakaoWebViewModel(application, KakaoWebRepositoryImpl())
    private lateinit var kakaoWebAdapter: KakaoWebAdapter

    override fun onDataBinding() {
        // mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.vm = kakaoWebViewModel
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
                    kakaoWebViewModel.requestKakaoWeb(binding.edSearch.text.toString(), 10)
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
        kakaoWebAdapter = KakaoWebAdapter(kakaoWebViewModel)
        binding.recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = kakaoWebAdapter
            addItemDecoration(decoration)
        }
    }

    private fun observeMainViewModel() {

        val owner = this
        with(kakaoWebViewModel) {
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
