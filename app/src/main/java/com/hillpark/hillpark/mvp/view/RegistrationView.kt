package com.hillpark.hillpark.mvp.view

import com.arellomobile.mvp.MvpView

interface RegistrationView : MvpView {
    fun showSnackBar(message: String)
    fun finishRegister()
}