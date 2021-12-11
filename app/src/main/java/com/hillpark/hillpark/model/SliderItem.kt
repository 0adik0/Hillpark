package com.hillpark.hillpark.model

import android.graphics.Bitmap

data class SliderItem (
    var title: String,
    var image: Bitmap,
    var htmlUrl: String,
    var widget: String
)