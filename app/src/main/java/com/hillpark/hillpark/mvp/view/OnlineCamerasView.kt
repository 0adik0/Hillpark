package com.hillpark.hillpark.mvp.view

import com.arellomobile.mvp.MvpView
import com.hillpark.hillpark.model.CameraUrl

interface OnlineCamerasView : MvpView {
    fun showCameras(list: List<CameraUrl>)
    fun showLoading()
    fun hideLoading()
    fun showSnackbar(message: String)
}