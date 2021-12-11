package com.hillpark.hillpark.mvp.view

import com.arellomobile.mvp.MvpView

interface LoginView : MvpView {
    fun showSnackBar(message: String)
    fun showSnackBarInfo(message: String)
    fun startApp()
    fun showLoading()
    fun hideLoading()
}