package com.rkddlsgur983.test.view.weather

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.rkddlsgur983.test.R
import com.rkddlsgur983.test.base.BaseActivity
import com.rkddlsgur983.test.databinding.ActivityWeatherBinding
import com.rkddlsgur983.test.view.weather.adapter.WeatherAdapter
import com.rkddlsgur983.test.view.weather.data.WeatherRepositoryImpl

class WeatherActivity: BaseActivity<ActivityWeatherBinding>() {
    override val layoutRes = R.layout.activity_weather
    private val weatherViewModel = WeatherViewModel(application, WeatherRepositoryImpl())

    override fun onDataBinding() {
        binding.vm = weatherViewModel
        super.onDataBinding()
    }

    override fun setupView() {
        super.setupView()
        initRecyclerViewWeather()
    }

    private fun initRecyclerViewWeather() {

        val linearLayoutManager = LinearLayoutManager(this)
        val weatherAdapter = WeatherAdapter(weatherViewModel)
        with(binding.rcvWeather) {
            layoutManager = linearLayoutManager
            adapter = weatherAdapter
        }

        weatherViewModel.weatherItemLiveData.observe(this, Observer {
            weatherAdapter.addAll(it)
        })
        weatherViewModel.requestWeather()
    }
}
