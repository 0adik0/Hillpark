package com.hillpark.hillpark.mvp.view

import com.arellomobile.mvp.MvpView
import com.hillpark.hillpark.model.CardOperation

interface CardHistoryView : MvpView{
    fun showHistory(list: List<CardOperation>)
    fun showSnackbar(message: String)
    fun showLoading()
    fun hideLoading()
}