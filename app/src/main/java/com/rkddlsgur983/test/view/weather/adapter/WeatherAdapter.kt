package com.rkddlsgur983.test.view.weather.adapter

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.rkddlsgur983.test.BR
import com.rkddlsgur983.test.R
import com.rkddlsgur983.test.base.BaseViewHolder
import com.rkddlsgur983.test.databinding.ItemWeatherBinding
import com.rkddlsgur983.test.view.weather.WeatherViewModel
import com.rkddlsgur983.test.view.weather.entity.WeatherItem

class WeatherAdapter(private val viewModel: WeatherViewModel): RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    private val weatherItemList =  mutableListOf<WeatherItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(viewModel, R.layout.item_weather, parent)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(weatherItemList[position])
    }

    override fun getItemCount() = weatherItemList.size

    fun addAll(weatherItemList: MutableList<WeatherItem>) {
        val oldSize = itemCount
        this.weatherItemList.addAll(weatherItemList)
        notifyItemRangeInserted(oldSize, weatherItemList.size)
    }

    fun clear() {
        weatherItemList.clear()
        notifyDataSetChanged()
    }

    class WeatherViewHolder(
        viewModel: WeatherViewModel,
        @LayoutRes layoutResId: Int,
        parent: ViewGroup
    ): BaseViewHolder<ItemWeatherBinding>(layoutResId, parent) {

        init {
            binding.vm = viewModel
        }

        fun bind(weatherItem: WeatherItem) {
            binding.setVariable(BR.weatherItem, weatherItem)
            binding.executePendingBindings()
        }
    }
}