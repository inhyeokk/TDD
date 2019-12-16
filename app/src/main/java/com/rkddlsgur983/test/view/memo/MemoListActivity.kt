package com.rkddlsgur983.test.view.memo

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rkddlsgur983.test.R
import com.rkddlsgur983.test.base.BaseActivity
import com.rkddlsgur983.test.databinding.ActivityMemoListBinding
import com.rkddlsgur983.test.util.BasicUtils
import com.rkddlsgur983.test.view.login.data.ApplicationDelegateImpl
import com.rkddlsgur983.test.view.memo.adapter.MemoAdapter
import com.rkddlsgur983.test.view.memo.entity.MemoViewType

class MemoListActivity: BaseActivity<ActivityMemoListBinding>() {

    companion object {
        const val REQUEST_MEMO_ADD = 1001
        const val REQUEST_MEMO_DETAIL = 1002
    }

    override val layoutRes = R.layout.activity_memo_list
    private lateinit var memoListViewModel:MemoListViewModel
    private lateinit var memoAdapter: MemoAdapter

    override fun onDataBinding() {
        memoListViewModel = MemoListViewModel(ApplicationDelegateImpl(application))
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
            showMessageEvent.observe(owner, Observer {
                BasicUtils.showToast(owner, it)
            })
            moveViewEvent.observe(owner, Observer {
                moveView(it)
            })
        }
    }

    private fun moveView(viewType: MemoViewType) {
        when (viewType) {
            MemoViewType.ADD -> {
                val intent = Intent(this, MemoAddActivity::class.java)
                startActivityForResult(intent, REQUEST_MEMO_ADD)
            }
            MemoViewType.DETAIL -> {
                val intent = Intent(this, MemoDetailActivity::class.java)
                startActivityForResult(intent, REQUEST_MEMO_DETAIL)
            }
            else -> {}
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_MEMO_ADD -> {
                when (resultCode) {
                    Activity.RESULT_OK -> {
                        memoAdapter.add(data!!.getParcelableExtra(MemoAddActivity.EXTRA_MEMO_ITEM)!!)
                    }
                }
            }
        }
    }
}
