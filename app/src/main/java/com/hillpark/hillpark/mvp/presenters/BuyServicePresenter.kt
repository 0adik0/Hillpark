package com.hillpark.hillpark.mvp.presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.hillpark.hillpark.AppClass
import com.hillpark.hillparkApp.R
import com.hillpark.hillpark.model.Card
import com.hillpark.hillpark.model.Service
import com.hillpark.hillpark.mvp.view.BuyServiceView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ru.avtoapp.partner.base.mvp.BasePresenter

@InjectViewState
class BuyServicePresenter : BasePresenter<BuyServiceView>() {
    fun loadCards(){
        viewState.showLoading()
        unsubscribeOnDestroy(
            AppClass.getServerComponent().serverApi.getCardsList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                            cardsListResponse ->
                        var ctr = 0
                        val list = ArrayList<Card>()
                        while (ctr<cardsListResponse.body()!!.packetBalance.valueList.get(1).array.cardValueList.size){
                            var ctr2 = 0
                            while (cardsListResponse.body()!!.packetBalance.valueList.get(1).array.cardValueList.get(ctr).structure.string.get(ctr2) != "code")ctr2++
                            val code = cardsListResponse.body()!!.packetBalance.valueList.get(1).array.cardValueList.get(ctr).structure.balanceValue.get(ctr2).element
                            ctr2 = 0
                            while (cardsListResponse.body()!!.packetBalance.valueList.get(1).array.cardValueList.get(ctr).structure.string.get(ctr2) != "permanent_rule")ctr2++
                            val rule = cardsListResponse.body()!!.packetBalance.valueList.get(1).array.cardValueList.get(ctr).structure.balanceValue.get(ctr2).personOptional.name

                            ctr2 = 0
                            while (cardsListResponse.body()!!.packetBalance.valueList.get(1).array.cardValueList.get(ctr).structure.string.get(ctr2) != "name") ctr2++
                            var name = cardsListResponse.body()!!.packetBalance.valueList.get(1).array.cardValueList.get(ctr).structure.balanceValue.get(ctr2).personOptional.name
                            if(name==""){
                                name = code
                            }

                            list.add(Card(code = code, rule = rule, name = name))
                            ctr++
                        }
                        viewState.hideLoading()
                        viewState.showCards(list)
                    },
                    onError = {e -> e.printStackTrace();viewState.hideLoading()}
                )
        )
    }

    fun loadServices(rule: String){
        unsubscribeOnDestroy(
        AppClass.getServerComponent().serverApi.getServices("s:" + rule)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {response ->
                    var ctr = 0
                    val list = ArrayList<Service>()
                    Log.e("dddddd", response.packetBalance.valueList.size.toString())
                    if(response.packetBalance.valueList[1].array != null) {
                        while (ctr < response.packetBalance.valueList[1].array.balanceValueList.size) {
                            var ctr2 = 0
                            while (response.packetBalance.valueList[1].array.balanceValueList[ctr].structure.string[ctr2] != "enabled") ctr2++
                            val enabled =
                                response.packetBalance.valueList[1].array.balanceValueList[ctr].structure.balanceValue[ctr2].element

                            ctr2 = 0
                            while (response.packetBalance.valueList[1].array.balanceValueList[ctr].structure.string[ctr2] != "name") ctr2++
                            val title =
                                response.packetBalance.valueList[1].array.balanceValueList[ctr].structure.balanceValue[ctr2].element

                            ctr2 = 0
                            while (response.packetBalance.valueList[1].array.balanceValueList[ctr].structure.string[ctr2] != "package_cost") ctr2++
                            var cost =
                                response.packetBalance.valueList[1].array.balanceValueList[ctr].structure.balanceValue[ctr2].decimal.decimalValue

                            if(cost.contains("."))cost = cost.substring(0, cost.indexOf("."))

                            if(enabled.contains("enabled"))list.add(Service(title, enabled, cost))
                            ctr++
                        }
                    }
                    viewState.showServices(list)
                },
                onError = {e -> e.printStackTrace()}
            )
        )
    }

    fun buyService(curCardCode: String, curService: String){
        viewState.showLoading()
        unsubscribeOnDestroy(
        AppClass.getServerComponent().serverApi.buyService("s:" + curCardCode,"s:" + curService)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {response ->
                    viewState.hideLoading()
                    if(response.code()==200)viewState.showSuccessDialog()
                    else viewState.showSnackBar(AppClass.getContext().resources.getString(R.string.buy_service_error_pay))
                },
                onError = {e ->
                    e.printStackTrace()
                    viewState.hideLoading()}
            )
        )
    }

    fun getCardBalance(code: String){
        unsubscribeOnDestroy(
            AppClass.getServerComponent().serverApi.getCardBalance("s:" + code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                            response ->
                        var ctr=0
                        while(response.packetBalance.valueList[1].array.balanceValueList[0].structure.string[ctr] != "balance")ctr++
                        Log.e("fdf", ctr.toString())
                        val balance = response.packetBalance.valueList[1].array.balanceValueList[0].structure.balanceValue[ctr].decimal.decimalValue
                        viewState.showCardBalance(balance)
                    },
                    onError = {e -> e.printStackTrace(); viewState.showCardBalance("")}
                )
        )
    }
}