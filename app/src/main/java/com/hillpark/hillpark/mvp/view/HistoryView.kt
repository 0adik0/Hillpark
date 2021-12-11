package com.hillpark.hillpark.mvp.view

import com.arellomobile.mvp.MvpView
import com.hillpark.hillpark.model.Card

interface HistoryView : MvpView {
    fun showCards(list: List<Card>)
    fun showSnackbar(message: String)
    fun showLoading()
    fun hideLoading()
    fun cardClicked(code: String)
}