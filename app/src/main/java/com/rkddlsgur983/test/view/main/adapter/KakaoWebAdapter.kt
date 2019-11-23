package com.rkddlsgur983.test.view.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.rkddlsgur983.test.BR
import com.rkddlsgur983.test.R
import com.rkddlsgur983.test.databinding.ItemKakaoWebBinding
import com.rkddlsgur983.test.view.main.entity.KakaoWebItem

class KakaoWebAdapter: RecyclerView.Adapter<KakaoWebAdapter.KakaoWebViewHolder>() {

    private val kakaoWebItemList =  ArrayList<KakaoWebItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KakaoWebViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemKakaoWebBinding = DataBindingUtil.inflate(layoutInflater,
            R.layout.item_kakao_web, parent, false)
        return KakaoWebViewHolder(binding)
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
        private val binding: ItemKakaoWebBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(kakaoWebItem: KakaoWebItem) {
            binding.setVariable(BR.kakaoWebItem, kakaoWebItem)
            binding.executePendingBindings()
        }
    }
}