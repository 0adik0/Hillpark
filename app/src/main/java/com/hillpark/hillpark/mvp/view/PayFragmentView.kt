package com.hillpark.hillpark.mvp.view

import com.arellomobile.mvp.MvpView

interface PayFragmentView : MvpView {
    fun showPaymentWindow(url: String)
}