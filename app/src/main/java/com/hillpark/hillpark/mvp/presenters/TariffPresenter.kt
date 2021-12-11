package com.hillpark.hillpark.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.hillpark.hillpark.AppClass
import com.hillpark.hillpark.model.TariffCategoryModel
import com.hillpark.hillpark.mvp.view.TariffsView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ru.avtoapp.partner.base.mvp.BasePresenter

@InjectViewState
class TariffPresenter : BasePresenter<TariffsView>() {
    fun loadTariffCategories(){
        viewState.showLoading()
        unsubscribeOnDestroy(
        AppClass.getServerComponent().serverApi.getTariffCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                        response ->
                    val list = ArrayList<TariffCategoryModel>()
                    if(response.code()==200){
                        var ctr = 0
                        while(ctr<response.body()!!.packetBalance.valueList[1].array.balanceValueList.size){

                            var ctr2 = 0
                            while(response.body()!!.packetBalance.valueList[1].array.balanceValueList[ctr].structure.string[ctr2]!="id")ctr2++
                            val id = response.body()!!.packetBalance.valueList[1].array.balanceValueList[ctr].structure.balanceValue[ctr2].personOptional.int_def

                            ctr2 = 0
                            while(response.body()!!.packetBalance.valueList[1].array.balanceValueList[ctr].structure.string[ctr2]!="name")ctr2++
                            val name = response.body()!!.packetBalance.valueList[1].array.balanceValueList[ctr].structure.balanceValue[ctr2].personOptional.name

                            list.add(
                                TariffCategoryModel(
                                    id = id,
                                    title = name
                                )
                            )
                            ctr++
                        }
                        viewState.hideLoading()
                        viewState.showTariffCategories(list)
                    }else{
                        viewState.hideLoading()
                        viewState.showSnackbar("Ошибка при загрузке тарифов")
                    }
                },
                onError = {e -> e.printStackTrace(); viewState.hideLoading();viewState.showSnackbar("Ошибка при загрузке тарифов")}
            )
        )
    }
}