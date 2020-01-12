package com.rkddlsgur983.test.view.memo

import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import com.rkddlsgur983.test.R
import com.rkddlsgur983.test.base.BaseActivity
import com.rkddlsgur983.test.databinding.ActivityMemoAddBinding
import com.rkddlsgur983.test.model.memo.MemoDatabase
import com.rkddlsgur983.test.model.memo.data.MemoRepository
import com.rkddlsgur983.test.view.login.data.ApplicationDelegateImpl
import com.rkddlsgur983.test.view.memo.entity.MemoCategory

class MemoAddActivity: BaseActivity<ActivityMemoAddBinding>() {

    override val layoutRes = R.layout.activity_memo_add
    private lateinit var memoAddViewModel: MemoAddViewModel

    override fun onDataBinding() {
        memoAddViewModel = MemoAddViewModel(
            ApplicationDelegateImpl(application),
            MemoRepository(MemoDatabase.getDatabase(this))
        )
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
        val owner = this
        val items = arrayOf(
            MemoCategory.PLEASE_CLICK.value,
            MemoCategory.WORK_TO_DO.value,
            MemoCategory.IDEA.value,
            MemoCategory.ETC.value
        )
        binding.spinnerCategory.apply {
            adapter = ArrayAdapter(owner, android.R.layout.simple_spinner_dropdown_item, items)
            onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    when (position) {
                        0 -> memoAddViewModel.onUpdateCategory(MemoCategory.PLEASE_CLICK)
                        1 -> memoAddViewModel.onUpdateCategory(MemoCategory.WORK_TO_DO)
                        2 -> memoAddViewModel.onUpdateCategory(MemoCategory.IDEA)
                        3 -> memoAddViewModel.onUpdateCategory(MemoCategory.ETC)
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        }
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
                finish()
            })
        }
    }
}
