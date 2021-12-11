package ru.avtoapp.partner.base.mvp

import com.arellomobile.mvp.MvpView
import com.hillpark.hillpark.model.Card

interface BaseView : MvpView {
    fun hideLoading()
    fun showLoading()
    fun showSnackbar(message: String)
    fun showCards(list: List<Card>)
    fun cardClicked(code: String)
}