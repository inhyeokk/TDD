package com.rkddlsgur983.test.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder<B: ViewDataBinding>(val binding: B): RecyclerView.ViewHolder(binding.root) {

    constructor(@LayoutRes layoutResId: Int, parent: ViewGroup)
            : this(DataBindingUtil.inflate<B>(LayoutInflater.from(parent.context), layoutResId, parent, false))
}