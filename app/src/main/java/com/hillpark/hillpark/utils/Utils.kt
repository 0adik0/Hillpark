package com.hillpark.hillpark.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.util.Base64
import android.widget.EditText
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.UnsupportedEncodingException
import java.lang.Exception
import java.nio.charset.Charset


class Utils {
    companion object{
        public fun getMonth(month: Int): String{
            return when(month){
                0 -> "Января"
                1 -> "Февраля"
                2 -> "Марта"
                3 -> "Апреля"
                4 -> "Мая"
                5 -> "Июня"
                6 -> "Июля"
                7 -> "Августа"
                8 -> "Сентября"
                9 -> "Октября"
                10 -> "Ноября"
                11 -> "Декабря"
                else -> "err"
            }
        }

        public fun getMonthForApi(month: String): String{
            return when(month){
                "01" -> "Января"
                "02" -> "Февраля"
                "03" -> "Марта"
                "04" -> "Апреля"
                "05" -> "Мая"
                "06" -> "Июня"
                "07" -> "Июля"
                "08" -> "Августа"
                "09" -> "Сентября"
                "10" -> "Октября"
                "11" -> "Ноября"
                "12" -> "Декабря"
                else -> "err"
            }
        }

        public fun getMontForRequest(month: String): String{
            if(month.contains("Января"))return "01"
            if(month.contains("Февраля"))return "02"
            if(month.contains("Марта"))return "03"
            if(month.contains("Апреля"))return "04"
            if(month.contains("Мая"))return "05"
            if(month.contains("Июня"))return "06"
            if(month.contains("Июля"))return "07"
            if(month.contains("Августа"))return "08"
            if(month.contains("Сентября"))return "09"
            if(month.contains("Октября"))return "10"
            if(month.contains("Ноября"))return "11"
            if(month.contains("Декабря"))return "12"
            return "00"
        }

        fun disableEditText(editText: EditText) {
            editText.isFocusable = false
            editText.isEnabled = false
            editText.isCursorVisible = false
            editText.keyListener = null
            editText.setBackgroundColor(Color.TRANSPARENT)
        }

        fun decodeBase64(base64String: String) : Observable<Bitmap>{
            return Observable.create(ObservableOnSubscribe<Bitmap> {
                emitter ->
                try {
                    val imageByteArray = Base64.decode(base64String, Base64.DEFAULT)
                    val decodedByte =
                        BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.size)
                    emitter.onNext(decodedByte)
                }catch (e: Exception){
                    emitter.onError(e)
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.single())
        }
    }
}