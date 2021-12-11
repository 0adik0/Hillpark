package com.hillpark.hillpark.mvp.view

import com.arellomobile.mvp.MvpView
import com.hillpark.hillpark.model.Card
import com.hillpark.hillpark.model.Service

interface BuyServiceView : MvpView {
    fun showCards(cards: List<Card>)
    fun showServices(services: List<Service>)
    fun showLoading()
    fun hideLoading()
    fun showSnackBar(message: String)
    fun showCardBalance(balance: String)
    fun showSuccessDialog()
}