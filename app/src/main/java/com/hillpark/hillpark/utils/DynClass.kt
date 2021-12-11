package com.hillpark.hillpark.utils

import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.hillpark.hillpark.model.SliderItem

class DynClass {
    companion object{
        public lateinit var cookieJar: PersistentCookieJar
        lateinit var sliderImageList: ArrayList<SliderItem>

        fun initSliderImageList(list: ArrayList<SliderItem>){
            sliderImageList = ArrayList(list)
        }

        fun isSliderImageListInitialized() : Boolean{
            return ::sliderImageList.isInitialized
        }
    }
}