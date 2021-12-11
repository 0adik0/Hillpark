package com.hillpark.hillpark.mvp.view

import com.arellomobile.mvp.MvpView
import com.hillpark.hillpark.model.TariffCategoryModel

interface TariffsView : MvpView {
    fun showLoading()
    fun hideLoading()
    fun showSnackbar(message: String)
    fun showTariffCategories(list: List<TariffCategoryModel>)
}