package com.rkddlsgur983.test.view.memo.adapter

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.rkddlsgur983.test.BR
import com.rkddlsgur983.test.R
import com.rkddlsgur983.test.base.BaseViewHolder
import com.rkddlsgur983.test.databinding.ItemMemoBinding
import com.rkddlsgur983.test.view.memo.MemoListViewModel
import com.rkddlsgur983.test.view.memo.entity.MemoItem

class MemoAdapter(private val viewModel: MemoListViewModel): RecyclerView.Adapter<MemoAdapter.MemoViewHolder>() {

    private val memoItemList = mutableListOf<MemoItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        return MemoViewHolder(viewModel, R.layout.item_memo, parent)
    }

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
        holder.bind(memoItemList[position])
    }

    override fun getItemCount() = memoItemList.size

    fun add(memoItem: MemoItem) {
        memoItemList.add(memoItem)
        notifyItemInserted(itemCount-1)
    }

    fun addAll(memoItemList: MutableList<MemoItem>) {
        val oldSize = itemCount
        this.memoItemList.addAll(memoItemList)
        notifyItemRangeInserted(oldSize, memoItemList.size)
    }

    fun clear() {
        memoItemList.clear()
        notifyDataSetChanged()
    }

    class MemoViewHolder(
        viewModel: MemoListViewModel,
        @LayoutRes layoutResId: Int,
        parent: ViewGroup
    ): BaseViewHolder<ItemMemoBinding>(layoutResId, parent) {

        init {
            binding.vm = viewModel
        }

        fun bind(memoItem: MemoItem) {
            binding.setVariable(BR.memoItem, memoItem)
            binding.executePendingBindings()
        }
    }
}