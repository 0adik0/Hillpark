package com.hillpark.hillpark.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.hillpark.hillpark.AppClass
import com.hillpark.hillpark.model.CardOrder
import com.hillpark.hillpark.mvp.view.CardOrdersView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ru.avtoapp.partner.base.mvp.BasePresenter

@InjectViewState
class CardOrdersPresenter : BasePresenter<CardOrdersView>() {
    fun loadOrders(code: String){
        viewState.showLoading()
        unsubscribeOnDestroy(
            AppClass.getServerComponent().serverApi.getCardOrders("s:" + code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                            response ->
                        if(response.code()==200){
                            val list: ArrayList<CardOrder> = ArrayList()
                            var ctr = 0
                            while(ctr<response.body()!!.packetBalance.valueList[1].array.balanceValueList.size){
                                var ctr2 = 0
                                while(response.body()!!.packetBalance.valueList[1].array.balanceValueList[ctr].structure.string[ctr2]!="filed_at")ctr2++
                                val date = response.body()!!.packetBalance.valueList[1].array.balanceValueList[ctr].structure.balanceValue[ctr2].time.timeString

                                ctr2 = 0
                                while(response.body()!!.packetBalance.valueList[1].array.balanceValueList[ctr].structure.string[ctr2]!="package_cost")ctr2++
                                val cost = response.body()!!.packetBalance.valueList[1].array.balanceValueList[ctr].structure.balanceValue[ctr2].decimal.decimalValue

                                ctr2 = 0
                                while(response.body()!!.packetBalance.valueList[1].array.balanceValueList[ctr].structure.string[ctr2]!="service_rulename")ctr2++
                                val title = response.body()!!.packetBalance.valueList[1].array.balanceValueList[ctr].structure.balanceValue[ctr2].personOptional.name

                                list.add(CardOrder(title = title, price = cost, time = date))
                                ctr++
                            }
                            viewState.hideLoading()
                            viewState.showOrders(list)
                        }else{
                            viewState.hideLoading()
                            viewState.showSnackbar("Ошибка при загрузке заказов")
                        }

                    },
                    onError = {
                            e -> e.printStackTrace()
                        viewState.hideLoading()
                        viewState.showSnackbar("Ошибка при загрузке заказов")
                    }
                )
        )
    }
}