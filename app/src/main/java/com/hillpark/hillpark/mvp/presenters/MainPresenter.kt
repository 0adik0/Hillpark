package com.hillpark.hillpark.mvp.presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.hillpark.hillpark.AppClass
import com.hillpark.hillpark.model.SliderItem
import com.hillpark.hillpark.model.SliderServerItem
import com.hillpark.hillpark.mvp.view.MainFragmentView
import com.hillpark.hillpark.utils.DynClass
import com.hillpark.hillpark.utils.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ru.avtoapp.partner.base.mvp.BasePresenter

@InjectViewState
class MainPresenter : BasePresenter<MainFragmentView>() {
    
    var readyCtr = 0
    
    fun loadSlider(){
        unsubscribeOnDestroy(
        AppClass.getServerComponent().serverApi.loadSlider()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = {response ->
                    readyCtr = 0
                    if(response.code()==200){
                        var ctr = 0
                        var list = ArrayList<SliderServerItem>()
                        while(ctr<response.body()!!.packetBalance.valueList[1].array.balanceValueList.size){
                            var ctr2 = 0
                            while (response.body()!!.packetBalance.valueList[1].array.balanceValueList[ctr].structure.string[ctr2]!="html_link")ctr2++
                            val htmlLink = response.body()!!.packetBalance.valueList[1].array.balanceValueList[ctr].structure.balanceValue[ctr2].personOptional.name

                            ctr2 = 0
                            while (response.body()!!.packetBalance.valueList[1].array.balanceValueList[ctr].structure.string[ctr2]!="image")ctr2++
                            val image64 = response.body()!!.packetBalance.valueList[1].array.balanceValueList[ctr].structure.balanceValue[ctr2].base64

                            ctr2 = 0
                            while (response.body()!!.packetBalance.valueList[1].array.balanceValueList[ctr].structure.string[ctr2]!="title")ctr2++
                            val title = response.body()!!.packetBalance.valueList[1].array.balanceValueList[ctr].structure.balanceValue[ctr2].personOptional.name

                            ctr2 = 0
                            while (response.body()!!.packetBalance.valueList[1].array.balanceValueList[ctr].structure.string[ctr2]!="widget")ctr2++
                            val widget = response.body()!!.packetBalance.valueList[1].array.balanceValueList[ctr].structure.balanceValue[ctr2].personOptional.name
                            
                            list.add(
                                SliderServerItem(
                                    htmlUrl = htmlLink,
                                    imageBase64 = image64,
                                    title = title,
                                    widget = widget

                                )
                            )
                            Log.e("item", "added")
                            ctr++
                        }

                        ctr = 0
                        while (ctr<list.size){
                            if(list[ctr].widget!="AD_WIDGET") {
                                list.removeAt(ctr)
                                ctr = 0
                            }
                            ctr++
                        }

                        ctr = 0
                        val sliderImageList = ArrayList<SliderItem>()
                        while (ctr<list.size){
                            unsubscribeOnDestroy(
                                Utils.decodeBase64(list[ctr].imageBase64).subscribeBy(
                                    onNext = {bmp ->
                                        sliderImageList.add(
                                            SliderItem(
                                            title = list[readyCtr].title,
                                            image = bmp,
                                            htmlUrl = list[readyCtr].htmlUrl,
                                            widget = list[readyCtr].widget
                                        ))
                                        readyCtr++
                                        if(readyCtr==list.size){
                                            DynClass.initSliderImageList(sliderImageList)
                                            viewState.sliderLoaded(sliderImageList)
                                        }
                                    },
                                    onError = {e -> e.printStackTrace(); readyCtr++}
                                )
                            )
                            ctr++
                        }
                    }
                },
                onError = {e -> e.printStackTrace()}
            )
        )
    }
}