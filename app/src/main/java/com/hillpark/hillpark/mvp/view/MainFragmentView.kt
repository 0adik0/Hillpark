package com.hillpark.hillpark.mvp.view

import com.arellomobile.mvp.MvpView
import com.hillpark.hillpark.model.SliderItem
import com.hillpark.hillpark.model.SliderServerItem

interface MainFragmentView : MvpView {
    fun sliderLoaded(list: ArrayList<SliderItem>)
    fun openUrl(url: String)
}