package com.hillpark.hillpark.model

data class CardInfo(
    var id: String,
    var name: String,
    var category: String,
    var type: String,
    var tatif: String,
    var workFrom: String,
    var workTo: String,
    var balance: String = ""
)