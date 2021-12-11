package com.hillpark.hillpark.mvp.presenters

import android.content.Context.MODE_PRIVATE
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.hillpark.hillpark.AppClass
import com.hillpark.hillparkApp.R
import com.hillpark.hillpark.constants.AppConst
import com.hillpark.hillpark.mvp.view.LoginView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ru.avtoapp.partner.base.mvp.BasePresenter

@InjectViewState
class LoginPresenter : BasePresenter<LoginView>() {
    fun login(login: String, password: String){
        unsubscribeOnDestroy(
        AppClass.getLoginComponent().loginApi.login("s:" + login,"s:" + password).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
            onSuccess = {
                    resultResponse ->
                if(resultResponse.code()==200) {
                    getProfile()
                }else{
                    viewState.hideLoading()
                    viewState.showSnackBar(AppClass.getContext().getString(R.string.login_err))
                }
            },
            onError = {e -> viewState.hideLoading();e.printStackTrace(); Log.e("OK", "NE OK")}
        )
        )
    }

    fun getProfile(){
        unsubscribeOnDestroy(
        AppClass.getServerComponent().serverApi.getProfile().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {personResponse ->
                    var ctr = 0
                    if(personResponse.code()==200) {
                        while (personResponse.body()!!.packetPerson.valueList[1].structure.name[ctr] != "first_name") ctr++
                        val first_name =
                            personResponse.body()!!.packetPerson.valueList[1].structure.personValue[ctr].personOptional.name

                        ctr = 0
                        while (personResponse.body()!!.packetPerson.valueList[1].structure.name[ctr] != "last_name") ctr++
                        val last_name =
                            personResponse.body()!!.packetPerson.valueList[1].structure.personValue[ctr].personOptional.name

                        AppClass.getContext()
                            .getSharedPreferences(AppConst.APP_PREFERENCES, MODE_PRIVATE).edit()
                            .putString(AppConst.FIRST_NAME, first_name).apply()
                        AppClass.getContext()
                            .getSharedPreferences(AppConst.APP_PREFERENCES, MODE_PRIVATE).edit()
                            .putString(AppConst.LAST_NAME, last_name).apply()
                        viewState.hideLoading()
                        viewState.startApp()
                        Log.e(
                            "resp",
                            personResponse.body()!!.packetPerson.valueList[1].structure.personValue[ctr].personOptional.name
                        )
                    }else{
                        viewState.hideLoading()
                        viewState.startApp()
                    }
                },
                onError = {e -> e.printStackTrace();viewState.hideLoading();viewState.startApp()}
            )
        )
    }

    fun restorePassword(login: String){
        unsubscribeOnDestroy(
        AppClass.getLoginComponent().loginApi.restorePassword("s:" + login)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = {response -> if(response.code()==200)viewState.showSnackBarInfo("На почту отправлено ссылка для восстановления пароля.") else {viewState.showSnackBarInfo("Ошибка восстановления пароля.")} },
                onError = {e -> e.printStackTrace(); viewState.showSnackBarInfo("Ошибка восстановления пароля.")}
            )
        )
    }
}