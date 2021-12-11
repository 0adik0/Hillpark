package com.hillpark.hillpark.mvp.view

import com.arellomobile.mvp.MvpView
import com.hillpark.hillpark.model.CardOrder

interface CardOrdersView : MvpView {
    fun showOrders(list: List<CardOrder>)
    fun showSnackbar(message: String)
    fun showLoading()
    fun hideLoading()
}