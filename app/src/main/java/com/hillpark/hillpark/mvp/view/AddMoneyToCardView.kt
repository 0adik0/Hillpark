package com.hillpark.hillpark.mvp.view

import com.arellomobile.mvp.MvpView
import com.hillpark.hillpark.model.Card

interface AddMoneyToCardView : MvpView {
    fun showCards(cards: List<Card>)
    fun showCardBalance(balance: String)
    fun showLoading()
    fun hideLoading(error: Boolean)
}