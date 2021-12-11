package com.hillpark.hillpark.utils

import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class waitUtils {
    companion object{
        fun wait1sec(): Observable<Any> {
            return Observable.create(ObservableOnSubscribe<Any> { emmiter -> Thread.sleep(1000); emmiter.onComplete()}).subscribeOn(
                Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        }
    }
}