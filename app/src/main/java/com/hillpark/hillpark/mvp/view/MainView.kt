package com.hillpark.hillpark.mvp.view

interface MainView {
    fun showBottomBar(checkedPosition: String)
    fun hideBottomBar()
    fun showNavigationBar()
    fun hideNavigationBar()
}