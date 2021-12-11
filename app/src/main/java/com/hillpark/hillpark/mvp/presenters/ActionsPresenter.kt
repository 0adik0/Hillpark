package com.hillpark.hillpark.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.hillpark.hillpark.AppClass
import com.hillpark.hillpark.model.ActionModel
import com.hillpark.hillpark.mvp.view.ActionsView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ru.avtoapp.partner.base.mvp.BasePresenter

@InjectViewState
class ActionsPresenter : BasePresenter<ActionsView>() {
    fun loadActions(){
        viewState.showLoading()
        unsubscribeOnDestroy(
        AppClass.getServerComponent().serverApi.getActions()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    response ->
                    val list = ArrayList<ActionModel>()
                    if(response.code()==200){
                        var ctr = 0
                        while(ctr<response.body()!!.packetBalance.valueList[1].array.balanceValueList.size){

                            var ctr2 = 0
                            while(response.body()!!.packetBalance.valueList[1].array.balanceValueList[ctr].structure.string[ctr2]!="image")ctr2++
                            var image64 = response.body()!!.packetBalance.valueList[1].array.balanceValueList[ctr].structure.balanceValue[ctr2].base64

                            ctr2 = 0
                            while(response.body()!!.packetBalance.valueList[1].array.balanceValueList[ctr].structure.string[ctr2]!="title")ctr2++
                            var title = response.body()!!.packetBalance.valueList[1].array.balanceValueList[ctr].structure.balanceValue[ctr2].personOptional.name

                            ctr2 = 0
                            while(response.body()!!.packetBalance.valueList[1].array.balanceValueList[ctr].structure.string[ctr2]!="html_link")ctr2++
                            var link = response.body()!!.packetBalance.valueList[1].array.balanceValueList[ctr].structure.balanceValue[ctr2].personOptional.name

                            ctr2 = 0
                            while (response.body()!!.packetBalance.valueList[1].array.balanceValueList[ctr].structure.string[ctr2]!="widget")ctr2++
                            val widget = response.body()!!.packetBalance.valueList[1].array.balanceValueList[ctr].structure.balanceValue[ctr2].personOptional.name

                            list.add(
                                ActionModel(
                                title = title,
                                image64 = image64,
                                link = link,
                                widget = widget
                            ))
                            ctr++
                        }
                        viewState.hideLoading()
                        viewState.showActions(list)
                    }else{
                        viewState.hideLoading()
                        viewState.showSnackbar("Ошибка при загрузке акций")
                    }
                },
                onError = {e -> e.printStackTrace(); viewState.hideLoading();viewState.showSnackbar("Ошибка при загрузке акций")}
            )
        )
    }
}