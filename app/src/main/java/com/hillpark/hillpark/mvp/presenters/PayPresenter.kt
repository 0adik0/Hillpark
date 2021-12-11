package com.hillpark.hillpark.mvp.presenters

import com.arellomobile.mvp.InjectViewState
import com.hillpark.hillpark.AppClass
import com.hillpark.hillpark.mvp.view.PayFragmentView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ru.avtoapp.partner.base.mvp.BasePresenter

@InjectViewState
class PayPresenter : BasePresenter<PayFragmentView>() {
    fun loadUrl(sum: Long, card: String){
        unsubscribeOnDestroy(
        AppClass.getServerComponent().serverApi.getPayUrl(success_url = "s:http://ab1024.computerica.com/public/mobile-payment-successfull.html",
                                                            fail_url = "s:http://ab1024.computerica.com/public/mobile-payment-failed.html",
                                                            card = "s:" + card,
                                                            amount = "decimal:s:" + sum.toString() + "&")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = {personResponse ->
                    var ctr = 0
                    while(personResponse.packetPerson.valueList[1].structure.name[ctr]!="action_url")ctr++
                    val url = personResponse.packetPerson.valueList[1].structure.personValue.get(ctr).element
                    viewState.showPaymentWindow(url)
                },
                onError = {e -> e.printStackTrace()}
            )
        )
    }
}