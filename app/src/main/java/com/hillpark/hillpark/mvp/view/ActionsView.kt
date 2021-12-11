package com.hillpark.hillpark.mvp.view

import com.arellomobile.mvp.MvpView
import com.hillpark.hillpark.model.ActionModel

interface ActionsView : MvpView {
    fun showLoading()
    fun hideLoading()
    fun showSnackbar(message: String)
    fun showActions(list: ArrayList<ActionModel>)
    fun imageClicked(url: String)
}