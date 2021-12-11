package com.hillpark.hillpark.mvp.presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.hillpark.hillpark.AppClass
import com.hillpark.hillpark.model.Card
import com.hillpark.hillpark.mvp.view.MyOrdersView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ru.avtoapp.partner.base.mvp.BasePresenter

@InjectViewState
class MyOrdersPresenter : BasePresenter<MyOrdersView>() {
    fun loadCards(){
        viewState.showLoading()
        unsubscribeOnDestroy(
            AppClass.getServerComponent().serverApi.getCardsList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = {
                            cardsListResponse ->
                        if(cardsListResponse.code()==200) {
                            var ctr = 0
                            val list = ArrayList<Card>()
                            while (ctr < cardsListResponse.body()!!.packetBalance.valueList.get(1).array.cardValueList.size) {
                                var ctr2 = 0
                                while (cardsListResponse.body()!!.packetBalance.valueList.get(1).array.cardValueList.get(ctr).structure.string.get(ctr2) != "code") ctr2++
                                val code = cardsListResponse.body()!!.packetBalance.valueList.get(1).array.cardValueList.get(ctr).structure.balanceValue.get(ctr2).element
                                Log.e("card ", cardsListResponse.body()!!.packetBalance.valueList.get(1).array.cardValueList.get(ctr).structure.balanceValue.get(ctr2).element)

                                ctr2 = 0
                                while (cardsListResponse.body()!!.packetBalance.valueList.get(1).array.cardValueList.get(ctr).structure.string.get(ctr2) != "permanent_rule") ctr2++
                                val rule = cardsListResponse.body()!!.packetBalance.valueList.get(1).array.cardValueList.get(ctr).structure.balanceValue.get(ctr2).personOptional.name
                                Log.e("card ", cardsListResponse.body()!!.packetBalance.valueList.get(1).array.cardValueList.get(ctr).structure.balanceValue.get(ctr2).personOptional.name)

                                ctr2 = 0
                                while (cardsListResponse.body()!!.packetBalance.valueList.get(1).array.cardValueList.get(ctr).structure.string.get(ctr2) != "name") ctr2++
                                var name = cardsListResponse.body()!!.packetBalance.valueList.get(1).array.cardValueList.get(ctr).structure.balanceValue.get(ctr2).personOptional.name
                                if(name==""){
                                    name = code
                                }

                                list.add(Card(name, code, rule))
                                ctr++
                            }
                            viewState.hideLoading()
                            viewState.showCards(list)
                        }else{
                            viewState.hideLoading()
                        }
                    },
                    onError = {e -> e.printStackTrace(); viewState.hideLoading();}
                )
        )
    }
}