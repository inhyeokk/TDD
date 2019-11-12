package com.rkddlsgur983.test.view.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.rkddlsgur983.test.BR
import com.rkddlsgur983.test.R
import com.rkddlsgur983.test.databinding.ItemWeatherBinding
import com.rkddlsgur983.test.view.main.entity.WeatherItem

class WeatherAdapter: RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    private val weatherItemList =  ArrayList<WeatherItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemWeatherBinding = DataBindingUtil.inflate(layoutInflater,
            R.layout.item_weather, parent, false)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(weatherItemList[position])
    }

    override fun getItemCount() = weatherItemList.size

    fun addAll(weatherItemList: ArrayList<WeatherItem>) {
        val oldSize = itemCount
        this.weatherItemList.addAll(weatherItemList)
        notifyItemRangeInserted(oldSize, weatherItemList.size)
    }

    fun clear() {
        weatherItemList.clear()
        notifyDataSetChanged()
    }

    class WeatherViewHolder(
        private val binding: ItemWeatherBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(weatherItem: WeatherItem) {
            binding.setVariable(BR.weatherItem, weatherItem)
            binding.executePendingBindings()
        }
    }
}