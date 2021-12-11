package com.hillpark.hillpark.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.hillpark.hillpark.AppClass
import com.hillpark.hillparkApp.R
import com.hillpark.hillpark.model.CardInfo
import com.hillpark.hillpark.mvp.view.CardEditView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ru.avtoapp.partner.base.mvp.BasePresenter

@InjectViewState
class CardEditPresenter : BasePresenter<CardEditView>() {

    fun loadCardInfo(code: String){
        viewState.showLoading()
        unsubscribeOnDestroy(
        AppClass.getServerComponent().serverApi.getCardInfo("s:" + code)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = {
                    response ->
                    if(response.code()==200){
                        var ctr = 0

                        while (response.body()!!.packetBalance.valueList[1].structure.string[ctr]!="category")ctr++
                        val category = response.body()!!.packetBalance.valueList[1].structure.balanceValue[ctr].element
                        ctr = 0

                        while (response.body()!!.packetBalance.valueList[1].structure.string[ctr]!="permanent_rule")ctr++
                        val type = response.body()!!.packetBalance.valueList[1].structure.balanceValue[ctr].element
                        ctr = 0

                        while (response.body()!!.packetBalance.valueList[1].structure.string[ctr]!="rate")ctr++
                        val tariff = response.body()!!.packetBalance.valueList[1].structure.balanceValue[ctr].element
                        ctr = 0

                        while (response.body()!!.packetBalance.valueList[1].structure.string[ctr]!="valid_from")ctr++
                        val workFrom = response.body()!!.packetBalance.valueList[1].structure.balanceValue[ctr].time.timeString
                        ctr = 0

                        while (response.body()!!.packetBalance.valueList[1].structure.string[ctr]!="valid_to")ctr++
                        val workTo = response.body()!!.packetBalance.valueList[1].structure.balanceValue[ctr].time.timeString
                        ctr = 0

                        while(response.body()!!.packetBalance.valueList[1].structure.string[ctr]!="accounts")ctr++
                        var balance = "0"
                        if(response.body()!!.packetBalance.valueList[1].structure.balanceValue[ctr].structure.balanceValue.size>0) {
                            balance = response.body()!!.packetBalance.valueList[1].structure.balanceValue[ctr].structure.balanceValue[0].decimal.decimalValue
                        }

                        val cardInfo = CardInfo(
                            id = code,
                            category = category,
                            type = type,
                            tatif = tariff,
                            workFrom = workFrom,
                            workTo = workTo,
                            balance = balance,
                            name = "in development"
                        )

                        viewState.showCardInfo(cardInfo)
                        viewState.hideLoading()
                    }else{
                        viewState.hideLoading()
                        viewState.showSnackBar(AppClass.getContext().resources.getString(R.string.card_edit_get_error))
                    }
                },
                onError = {e -> e.printStackTrace(); viewState.hideLoading(); viewState.showSnackBar(AppClass.getContext().resources.getString(R.string.card_edit_get_error))}
            )
        )
    }

    fun editCardName(code: String, name: String){
        unsubscribeOnDestroy(
        AppClass.getServerComponent().serverApi.renameCard("s:" + code, "s:" + name)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = {
                    response ->
                    if(response.code()==200){
                        viewState.hideLoading()
                        viewState.showSnackBar(AppClass.getContext().resources.getString(R.string.card_edit_change_name_success))
                    }else{
                        viewState.hideLoading()
                        viewState.showSnackBar(AppClass.getContext().resources.getString(R.string.card_edit_change_name_error))
                    }
                },
                onError = {e ->
                    e.printStackTrace()
                    viewState.hideLoading()
                    viewState.showSnackBar(AppClass.getContext().resources.getString(R.string.card_edit_change_name_error))
                }
            )
        )
    }
}