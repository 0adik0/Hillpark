package com.hillpark.hillpark.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.hillpark.hillpark.AppClass
import com.hillpark.hillpark.model.WeatherModel
import com.hillpark.hillpark.mvp.view.WeatherView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ru.avtoapp.partner.base.mvp.BasePresenter


@InjectViewState
class WeatherPresenter : BasePresenter<WeatherView>() {
    fun loadWeather(){
        viewState.showLoading()
        unsubscribeOnDestroy(
        AppClass.getServerComponent().serverApi.getWeather()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    response ->
                    if(response.code()==200) {
                        var ctr = 0
                        while (response.body()!!.packetBalance.valueList[1].structure.string[ctr]!="description_ru")ctr++
                        val desc = response.body()!!.packetBalance.valueList[1].structure.balanceValue[ctr].personOptional.name

                        ctr = 0
                        while (response.body()!!.packetBalance.valueList[1].structure.string[ctr]!="pressure")ctr++
                        val pressure = response.body()!!.packetBalance.valueList[1].structure.balanceValue[ctr].personOptional.int_def

                        ctr = 0
                        while (response.body()!!.packetBalance.valueList[1].structure.string[ctr]!="temperature")ctr++
                        val temp = response.body()!!.packetBalance.valueList[1].structure.balanceValue[ctr].personOptional.int_def

                        ctr = 0
                        while (response.body()!!.packetBalance.valueList[1].structure.string[ctr]!="wind_direction_ru")ctr++
                        val windDir = response.body()!!.packetBalance.valueList[1].structure.balanceValue[ctr].personOptional.name

                        ctr = 0
                        while (response.body()!!.packetBalance.valueList[1].structure.string[ctr]!="wind_speed")ctr++
                        val windSpeed = response.body()!!.packetBalance.valueList[1].structure.balanceValue[ctr].personOptional.int_def
                        
                        viewState.hideLoading()
                        viewState.showWeather(
                            WeatherModel(
                                description = desc,
                                pressure = pressure,
                                temperature = temp,
                                windDir = windDir,
                                windSpeed = windSpeed
                            )
                        )
                    }else{
                        viewState.showSnackbar("Ошибка при загрузке актуальной погоды")
                    }
                },
                onError = {e -> e.printStackTrace(); viewState.showSnackbar("Ошибка при загрузке актуальной погоды")}
            )
        )
    }
}