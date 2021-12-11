package ru.avtoapp.partner.base.mvp

import androidx.annotation.NonNull
import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter<View : MvpView> : MvpPresenter<View>() {
    private val mCompositeSubscription = CompositeDisposable()

    protected fun unsubscribeOnDestroy(@NonNull subscription: Disposable) {
        mCompositeSubscription.add(subscription)
    }

    override fun onDestroy() {
        super.onDestroy()
        mCompositeSubscription.clear()
    }
}