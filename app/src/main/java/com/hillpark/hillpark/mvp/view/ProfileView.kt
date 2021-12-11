package com.hillpark.hillpark.mvp.view

import com.arellomobile.mvp.MvpView
import com.hillpark.hillpark.model.ProfileModel

interface ProfileView : MvpView {
    fun showProfileData(profile: ProfileModel)
    fun showLoading()
    fun hideLoading()
    fun showSnackBar(message: String)
}