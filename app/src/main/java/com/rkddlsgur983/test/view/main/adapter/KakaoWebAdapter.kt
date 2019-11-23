package com.rkddlsgur983.test.view.main.adapter

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.rkddlsgur983.test.BR
import com.rkddlsgur983.test.R
import com.rkddlsgur983.test.base.BaseViewHolder
import com.rkddlsgur983.test.databinding.ItemKakaoWebBinding
import com.rkddlsgur983.test.view.main.MainViewModel
import com.rkddlsgur983.test.view.main.entity.KakaoWebItem

class KakaoWebAdapter(private val viewModel: MainViewModel): RecyclerView.Adapter<KakaoWebAdapter.KakaoWebViewHolder>() {

    private val kakaoWebItemList =  ArrayList<KakaoWebItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KakaoWebViewHolder {
        return KakaoWebViewHolder(viewModel, R.layout.item_kakao_web, parent)
    }

    override fun onBindViewHolder(holder: KakaoWebViewHolder, position: Int) {
        holder.bind(kakaoWebItemList[position])
    }

    override fun getItemCount() = kakaoWebItemList.size

    fun addAll(kakaoWebItemList: ArrayList<KakaoWebItem>) {
        val oldSize = itemCount
        this.kakaoWebItemList.addAll(kakaoWebItemList)
        notifyItemRangeInserted(oldSize, kakaoWebItemList.size)
    }

    fun clear() {
        kakaoWebItemList.clear()
        notifyDataSetChanged()
    }

    class KakaoWebViewHolder(
        viewModel: MainViewModel,
        @LayoutRes layoutResId: Int,
        parent: ViewGroup
    ): BaseViewHolder<ItemKakaoWebBinding>(layoutResId, parent) {

        init {
            binding.viewModel = viewModel
        }

        fun bind(kakaoWebItem: KakaoWebItem) {
            binding.setVariable(BR.kakaoWebItem, kakaoWebItem)
            binding.executePendingBindings()
        }
    }
}