package com.hillpark.hillpark.mvp.view

import com.arellomobile.mvp.MvpView
import com.hillpark.hillpark.model.WeatherModel

interface WeatherView : MvpView {
    fun showLoading()
    fun hideLoading()
    fun showSnackbar(message: String)
    fun showWeather(weather: WeatherModel)
}