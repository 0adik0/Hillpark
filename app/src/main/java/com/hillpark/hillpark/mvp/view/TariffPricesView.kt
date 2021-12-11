package com.hillpark.hillpark.mvp.view

import com.arellomobile.mvp.MvpView
import com.hillpark.hillpark.model.TariffItemPriceModel

interface TariffPricesView : MvpView {
    fun showLoading()
    fun hideLoading()
    fun showSnackbar(message: String)
    fun showTariffPrices(list: List<TariffItemPriceModel>)
}