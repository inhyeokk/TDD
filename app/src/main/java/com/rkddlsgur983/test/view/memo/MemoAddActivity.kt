package com.rkddlsgur983.test.view.memo

import android.app.Activity
import android.content.Intent
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import com.rkddlsgur983.test.R
import com.rkddlsgur983.test.base.BaseActivity
import com.rkddlsgur983.test.databinding.ActivityMemoAddBinding
import com.rkddlsgur983.test.util.BasicUtils
import com.rkddlsgur983.test.view.memo.entity.MemoCategory
import com.rkddlsgur983.test.view.memo.entity.MemoItem

class MemoAddActivity: BaseActivity<ActivityMemoAddBinding>() {

    companion object {
        const val EXTRA_MEMO_ITEM = "EXTRA_MEMO_ITEM"
    }

    override val layoutRes = R.layout.activity_memo_add
    private lateinit var memoAddViewModel: MemoAddViewModel

    override fun onDataBinding() {
        memoAddViewModel = MemoAddViewModel()
        binding.vm = memoAddViewModel
        super.onDataBinding()
    }

    override fun setupView() {
        initToolbar()
        initEditText()
        initSpinner()
        observeMemoAddViewModel()
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)
        with(supportActionBar!!) {
            title = getString(R.string.memo_add_tv_title)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun initEditText() {
        with(binding) {
            edTitle.doOnTextChanged { text, _, _, _ ->
                val value = text.toString()
                memoAddViewModel.onUpdateTitle(value)
            }
            edContents.doOnTextChanged { text, _, _, _ ->
                val value = text.toString()
                memoAddViewModel.onUpdateContents(value)
            }
        }
    }

    private fun initSpinner() {
        val items = arrayOf(
            getString(R.string.memo_add_spinner),
            MemoCategory.WORK_TO_DO.value,
            MemoCategory.IDEA.value,
            MemoCategory.ETC.value
        )
        binding.spinnerCategory.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun observeMemoAddViewModel() {

        val owner = this
        with(memoAddViewModel) {
            completeClickable.observe(owner, Observer {
                binding.btnComplete.apply {
                    isClickable = it
                    isSelected = it
                }
            })

            moveViewEvent.observe(owner, Observer {
                val intent = Intent()
                val memoItem = MemoItem(binding.edTitle.text.toString(), MemoCategory.WORK_TO_DO, binding.edContents.text.toString(), BasicUtils.getTime())
                intent.putExtra(EXTRA_MEMO_ITEM, memoItem)
                setResult(Activity.RESULT_OK, intent)
                finish()
            })
        }
    }
}
