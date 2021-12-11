package com.hillpark.hillpark.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.hillpark.hillpark.AppClass
import com.hillpark.hillpark.model.CameraUrl
import com.hillpark.hillpark.mvp.view.OnlineCamerasView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ru.avtoapp.partner.base.mvp.BasePresenter

@InjectViewState
class OnlineCamerasPresenter : BasePresenter<OnlineCamerasView>() {
    fun loadCameras(){
        viewState.showLoading()
        unsubscribeOnDestroy(
        AppClass.getServerComponent().serverApi.getCameras()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = {response ->
                    if (response.code()==200){
                        val list: ArrayList<CameraUrl> = ArrayList()
                        var ctr = 0
                        while(ctr<response.body()!!.packetBalance.valueList[1].array.balanceValueList.size){
                            var ctr2 = 0
                            while(response.body()!!.packetBalance.valueList[1].array.balanceValueList[ctr].structure.string[ctr2]!="name")ctr2++
                            val name = response.body()!!.packetBalance.valueList[1].array.balanceValueList[ctr].structure.balanceValue[ctr2].personOptional.name

                            ctr2 = 0
                            while(response.body()!!.packetBalance.valueList[1].array.balanceValueList[ctr].structure.string[ctr2]!="uri")ctr2++
                            val uri = response.body()!!.packetBalance.valueList[1].array.balanceValueList[ctr].structure.balanceValue[ctr2].personOptional.name

                            list.add(CameraUrl(name = name, url = uri))
                            ctr++
                        }
                        viewState.hideLoading()
                        viewState.showCameras(list)
                    }else{
                        viewState.hideLoading()
                        viewState.showSnackbar("Ошибка при загрузке камер")
                    }
                },
                onError = {e -> e.printStackTrace(); viewState.hideLoading(); viewState.showSnackbar("Ошибка при загрузке камер")}
            )
        )
    }
}