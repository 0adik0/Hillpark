package com.hillpark.hillpark.mvp.presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.hillpark.hillpark.AppClass
import com.hillpark.hillparkApp.R
import com.hillpark.hillpark.mvp.view.RegistrationView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ru.avtoapp.partner.base.mvp.BasePresenter

@InjectViewState
class RegistrationPresenter : BasePresenter<RegistrationView>(){
    fun register(code: String, email: String, phone: String?, password: String, secPassword: String, name: String?, lastName: String){
        unsubscribeOnDestroy(
        AppClass.getLoginComponent().loginApi.register(
            "s:$code",
            "s:$email",
            "s:$password",
            "s:$secPassword",
            "s:$phone",
            "s:$name",
            "s:$lastName"
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {response ->
                    if(response.isSuccessful) {
                        viewState.showSnackBar(AppClass.getContext().resources.getString(R.string.register_success))
                        viewState.finishRegister()
                    }else{
                        viewState.showSnackBar(AppClass.getContext().resources.getString(R.string.register_error))
                    }
                },
                onError = {e -> e.printStackTrace();viewState.showSnackBar(AppClass.getContext().resources.getString(R.string.register_error))}
            )
        )
    }
}