package com.rkddlsgur983.test.view.memo

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rkddlsgur983.test.R
import com.rkddlsgur983.test.base.BaseActivity
import com.rkddlsgur983.test.databinding.ActivityMemoListBinding
import com.rkddlsgur983.test.model.memo.MemoDatabase
import com.rkddlsgur983.test.model.memo.data.MemoRepository
import com.rkddlsgur983.test.util.BasicUtils
import com.rkddlsgur983.test.view.login.data.ApplicationDelegateImpl
import com.rkddlsgur983.test.view.memo.adapter.MemoAdapter
import com.rkddlsgur983.test.view.memo.entity.MemoViewType

class MemoListActivity: BaseActivity<ActivityMemoListBinding>() {

    override val layoutRes = R.layout.activity_memo_list
    private lateinit var memoListViewModel:MemoListViewModel
    private lateinit var memoAdapter: MemoAdapter

    override fun onDataBinding() {
        memoListViewModel = MemoListViewModel(
            ApplicationDelegateImpl(application),
            MemoRepository(MemoDatabase.getDatabase(this))
        )
        binding.vm = memoListViewModel
        super.onDataBinding()
    }

    override fun setupView() {
        initRecyclerView()
        observeMemoListViewModel()
    }

    private fun initRecyclerView() {

        val linearLayoutManager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(
            binding.rcvMemoList.context,
            linearLayoutManager.orientation
        )
        memoAdapter = MemoAdapter(memoListViewModel)
        binding.rcvMemoList.apply {
            layoutManager = linearLayoutManager
            adapter = memoAdapter
            addItemDecoration(decoration)
        }
    }

    private fun observeMemoListViewModel() {

        val owner = this
        with(memoListViewModel) {
            memoItemListLiveData.observe(owner, Observer {
                memoAdapter.reset(it)
            })
            showMessageEvent.observe(owner, Observer { message ->
                if (message.isNotEmpty()) {
                    BasicUtils.showToast(owner, message)
                }
            })
            moveViewEvent.observe(owner, Observer {
                moveView(it)
            })
        }
    }

    override fun onResume() {
        super.onResume()
        memoListViewModel.onLoadMemoFromDatabase()
    }

    private fun moveView(viewType: MemoViewType) {
        when (viewType) {
            MemoViewType.ADD -> {
                val intent = Intent(this, MemoAddActivity::class.java)
                startActivity(intent)
            }
            MemoViewType.DETAIL -> {
                val intent = Intent(this, MemoDetailActivity::class.java)
                startActivity(intent)
            }
            else -> {}
        }
    }
}
