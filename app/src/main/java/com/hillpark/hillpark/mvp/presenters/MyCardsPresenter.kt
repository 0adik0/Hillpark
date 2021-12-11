package com.hillpark.hillpark.mvp.presenters

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.hillpark.hillpark.AppClass
import com.hillpark.hillparkApp.R
import com.hillpark.hillpark.model.Card
import com.hillpark.hillpark.mvp.view.MyCardsView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ru.avtoapp.partner.base.mvp.BasePresenter

@InjectViewState
class MyCardsPresenter : BasePresenter<MyCardsView>() {
    fun addCard(code: String){
        viewState.showLoading()
        unsubscribeOnDestroy(
        AppClass.getServerComponent().serverApi.addCard("s:" + code)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = {response ->
                    viewState.hideLoading()
                    if(response.code()==200) {
                        viewState.showSnackBar(AppClass.getContext().resources.getString(R.string.my_cards_add_success))
                        loadCards()
                    } else {
                        viewState.showSnackBar(AppClass.getContext().resources.getString(R.string.my_cards_add_error))
                    }
                },
                onError = {e -> e.printStackTrace();viewState.hideLoading();viewState.showSnackBar(AppClass.getContext().resources.getString(R.string.my_cards_add_error))}
            )
        )
    }

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

    fun deleteCard(code: String){
        unsubscribeOnDestroy(
        AppClass.getServerComponent().serverApi.deleteCard("s:" + code)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = {response ->
                    if(response.code()==200) {
                        viewState.showSnackBar("Карта успешно удалена")
                        viewState.removeCard()
                    } else {
                        viewState.showSnackBar("Произошла ошибка при удалении карты")
                    }},
                onError = {e -> e.printStackTrace();viewState.showSnackBar("Произошла ошибка при удалении карты")}
            )
        )
    }
}