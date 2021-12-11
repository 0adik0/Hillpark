package com.hillpark.hillpark.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.hillpark.hillpark.AppClass
import com.hillpark.hillpark.model.CardOperation
import com.hillpark.hillpark.mvp.view.CardHistoryView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ru.avtoapp.partner.base.mvp.BasePresenter

@InjectViewState
class CardHistoryPresenter : BasePresenter<CardHistoryView>(){
    fun getOperations(code: String){
        viewState.showLoading()
        unsubscribeOnDestroy(
        AppClass.getServerComponent().serverApi.getCardOperations("s:" + code)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                        response ->
                    if(response.code()==200){
                        val list: ArrayList<CardOperation> = ArrayList()
                        var ctr = 0
                        while(ctr<response.body()!!.packetBalance.valueList[1].array.balanceValueList.size){
                            var ctr2 = 0
                            while(response.body()!!.packetBalance.valueList[1].array.balanceValueList[ctr].structure.string[ctr2]!="amount")ctr2++
                            val amount = response.body()!!.packetBalance.valueList[1].array.balanceValueList[ctr].structure.balanceValue[ctr2].decimal.decimalValue

                            ctr2 = 0
                            while(response.body()!!.packetBalance.valueList[1].array.balanceValueList[ctr].structure.string[ctr2]!="comment")ctr2++
                            val comment = response.body()!!.packetBalance.valueList[1].array.balanceValueList[ctr].structure.balanceValue[ctr2].element

                            ctr2 = 0
                            while(response.body()!!.packetBalance.valueList[1].array.balanceValueList[ctr].structure.string[ctr2]!="date")ctr2++
                            val date = response.body()!!.packetBalance.valueList[1].array.balanceValueList[ctr].structure.balanceValue[ctr2].time.timeString

                            var day : String = date.substring(0,8)
                            var time = date.substring(9,13)
                            day = day[6] + day[7].toString() + "." + day[4] + day[5] + "." + day[0] + day[1] + day[2] + day[3]
                            time = time[0] + time[1].toString() + ":" + time[2] + time[3]

                            list.add(CardOperation(amount = amount, title = comment, date = day, time = time))
                            ctr++
                        }
                        viewState.hideLoading()
                        viewState.showHistory(list)
                    }else{
                        viewState.hideLoading()
                        viewState.showSnackbar("Ошибка при загрузке операций")
                    }

                },
                onError = {
                        e -> e.printStackTrace()
                    viewState.hideLoading()
                    viewState.showSnackbar("Ошибка при загрузке операций")
                }
            )
        )
    }
}