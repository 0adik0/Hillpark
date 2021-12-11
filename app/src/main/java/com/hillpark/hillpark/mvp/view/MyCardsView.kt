package com.hillpark.hillpark.mvp.view

import com.arellomobile.mvp.MvpView
import com.hillpark.hillpark.model.Card

interface MyCardsView : MvpView {
    fun showSnackBar(message: String)
    fun showLoading()
    fun hideLoading()
    fun showCards(list: List<Card>)
    fun cardClicked(name: String, code: String)
    fun removeCard()
}