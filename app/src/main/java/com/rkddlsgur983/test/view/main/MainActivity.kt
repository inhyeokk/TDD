package com.rkddlsgur983.test.view.main

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.rkddlsgur983.test.R
import com.rkddlsgur983.test.base.BaseActivity
import com.rkddlsgur983.test.databinding.ActivityMainBinding
import com.rkddlsgur983.test.view.main.adapter.WeatherAdapter
import com.rkddlsgur983.test.view.main.data.MainRepositoryImpl

class MainActivity: BaseActivity<ActivityMainBinding>() {

    override val layoutRes = R.layout.activity_main
    private val mainViewModel = MainViewModel(MainRepositoryImpl())

    override fun onDataBinding() {
        // mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.vm = mainViewModel
        super.onDataBinding()
    }

    override fun setupView() {
        super.setupView()
        initRecyclerViewWeather()
    }

    private fun initRecyclerViewWeather() {

        val linearLayoutManager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(
            binding.rcvWeather.context,
            linearLayoutManager.orientation
        )
        val weatherAdapter = WeatherAdapter()
        binding.rcvWeather.apply {
            layoutManager = linearLayoutManager
            adapter = weatherAdapter
            addItemDecoration(decoration)
        }

        mainViewModel.observeWeatherItem().observeForever {
            weatherAdapter.addAll(it)
        }
        mainViewModel.requestWeather()
    }
}
