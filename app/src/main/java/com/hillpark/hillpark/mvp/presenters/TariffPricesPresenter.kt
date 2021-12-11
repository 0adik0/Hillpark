package com.hillpark.hillpark.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.hillpark.hillpark.AppClass
import com.hillpark.hillpark.model.TariffItemPriceModel
import com.hillpark.hillpark.mvp.view.TariffPricesView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ru.avtoapp.partner.base.mvp.BasePresenter

@InjectViewState
class TariffPricesPresenter : BasePresenter<TariffPricesView>(){
    fun loadTariffPrices(id: String){
        viewState.showLoading()
        unsubscribeOnDestroy(
        AppClass.getServerComponent().serverApi.getTariffPrices("i:" + id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = {
                        response ->
                    val list = ArrayList<TariffItemPriceModel>()
                    if(response.code()==200){
                        var ctr = 0
                        while(ctr<response.body()!!.packetBalance.valueList[1].array.balanceValueList.size){

                            var ctr2 = 0
                            while(response.body()!!.packetBalance.valueList[1].array.balanceValueList[ctr].structure.string[ctr2]!="name")ctr2++
                            var name = response.body()!!.packetBalance.valueList[1].array.balanceValueList[ctr].structure.balanceValue[ctr2].personOptional.name

                            ctr2 = 0
                            while(response.body()!!.packetBalance.valueList[1].array.balanceValueList[ctr].structure.string[ctr2]!="tariffname")ctr2++
                            var tariffname = response.body()!!.packetBalance.valueList[1].array.balanceValueList[ctr].structure.balanceValue[ctr2].personOptional.name

                            ctr2 = 0
                            while(response.body()!!.packetBalance.valueList[1].array.balanceValueList[ctr].structure.string[ctr2]!="prices")ctr2++
                            var price1 = response.body()!!.packetBalance.valueList[1].array.balanceValueList[ctr].structure.balanceValue[ctr2].balanceArray.balanceValues[0].structure.balanceValue[1].decimal.decimalValue
                            var price2 = response.body()!!.packetBalance.valueList[1].array.balanceValueList[ctr].structure.balanceValue[ctr2].balanceArray.balanceValues[1].structure.balanceValue[1].decimal.decimalValue

                            list.add(
                                TariffItemPriceModel(
                                    name = name,
                                    age = tariffname,
                                    weekTime = "Буднии/Выходные",
                                    price1 = price1,
                                    price2 = price2
                                ))
                            ctr++
                        }
                        viewState.hideLoading()
                        viewState.showTariffPrices(list)
                    }else{
                        viewState.hideLoading()
                        viewState.showSnackbar("Ошибка при загрузке цен")
                    }
                },
                onError = {e -> e.printStackTrace(); viewState.hideLoading();viewState.showSnackbar("Ошибка при загрузке цен")}
            )
        )
    }
}