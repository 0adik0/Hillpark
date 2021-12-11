package com.hillpark.hillpark.mvp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.google.android.material.snackbar.Snackbar
import com.hillpark.hillparkApp.R
import com.hillpark.hillpark.model.WeatherModel
import com.hillpark.hillpark.mvp.MainActivity
import com.hillpark.hillpark.mvp.presenters.WeatherPresenter
import com.hillpark.hillpark.mvp.view.WeatherView
import kotlinx.android.synthetic.main.fragment_weather.*

class WeatherFragment : MvpAppCompatFragment(), WeatherView {

    @InjectPresenter
    lateinit var presenter: WeatherPresenter
    private lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    override fun onStart() {
        super.onStart()
        mainActivity = activity as MainActivity
    }

    override fun onResume() {
        super.onResume()
        mainActivity.showMenuActBar("Информер погоды", mainActivity)
        mainActivity.hideBottomBar()
        presenter.loadWeather()
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
        constraint.visibility = View.INVISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
        constraint.visibility = View.VISIBLE
    }

    override fun showSnackbar(message: String) {
        Snackbar.make(root,message, Snackbar.LENGTH_SHORT).show()
    }

    override fun showWeather(weather: WeatherModel) {
        temp.text = weather.temperature + " °C"
        wind_direction.text = weather.windDir
        wind_speed.text = weather.windSpeed + " м/c"
        pressure.text = weather.pressure + " мм рт.ст."
        description.text = weather.description
    }
}