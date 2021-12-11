package com.hillpark.hillpark.mvp.view

import com.arellomobile.mvp.MvpView
import com.hillpark.hillpark.model.CardInfo

interface CardEditView : MvpView{
    fun showCardInfo(cardInfo: CardInfo)
    fun showLoading()
    fun hideLoading()
    fun showSnackBar(message: String)
    fun reload()

}