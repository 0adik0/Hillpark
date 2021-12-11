package com.hillpark.hillpark.mvp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.hillpark.hillparkApp.R
import com.hillpark.hillpark.adapters.CardAdapter
import com.hillpark.hillpark.adapters.ServiceAdapter
import com.hillpark.hillpark.constants.FragmentNames
import com.hillpark.hillpark.model.*
import com.hillpark.hillpark.mvp.MainActivity
import com.hillpark.hillpark.mvp.presenters.BuyServicePresenter
import com.hillpark.hillpark.mvp.view.BuyServiceView
import kotlinx.android.synthetic.main.fragment_buy_service.*
import kotlinx.android.synthetic.main.fragment_buy_service.cardNumberLabel
import kotlinx.android.synthetic.main.fragment_buy_service.cardRecycler
import kotlinx.android.synthetic.main.fragment_buy_service.constraint
import kotlinx.android.synthetic.main.fragment_buy_service.moneyLabel
import kotlinx.android.synthetic.main.fragment_buy_service.progressBar

class BuyServiceFragment : MvpAppCompatFragment(), BuyServiceView {

    @InjectPresenter
    lateinit var presenter: BuyServicePresenter

    private lateinit var curCardCode: String
    private lateinit var curService: String
    private lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_buy_service, container, false)
    }

    override fun onResume() {
        super.onResume()
        mainActivity.showActBar(getString(R.string.buy_service_bar_title))
        curCardCode = ""
        curService = ""
        moneyLabel.visibility = View.INVISIBLE
        cardNumberLabel.visibility = View.INVISIBLE
        buyServiceBtn.setOnClickListener {
            buyService()
        }
    }

    override fun onStart() {
        super.onStart()
        mainActivity = activity as MainActivity
        presenter.loadCards()
    }

    override fun showCards(cards: List<Card>) {
        cardRecycler.layoutManager = LinearLayoutManager(context)
        val singleCheckCardsGroup = ArrayList<SingleCheckGroup>()
        singleCheckCardsGroup.add(
            SingleCheckGroup(
                "Выберите карту:",
                cards
            )
        )
        val adapter =  CardAdapter(singleCheckCardsGroup, cards)
        cardRecycler.adapter = adapter
        adapter.setChildClickListener { v, checked, group, childIndex ->  Log.e("checked_card", cards.get(childIndex).code); curCardCode = cards.get(childIndex).code; presenter.loadServices(cards.get(childIndex).rule); presenter.getCardBalance(curCardCode);adapter.onGroupClick(0); } // @todo need to request services
    }

    override fun showServices(services: List<Service>) { // @todo need to show
        if(services.size>0) {
            serviceRecycler.layoutManager = LinearLayoutManager(context)
            val singleCheckCardsGroup = ArrayList<SingleCheckGroup>()
            singleCheckCardsGroup.add(
                SingleCheckGroup(
                    "Выберите услугу:",
                    services
                )
            )
            val adapter = ServiceAdapter(singleCheckCardsGroup, services)
            serviceRecycler.adapter = adapter
            adapter.setChildClickListener { v, checked, group, childIndex ->
                Log.e(
                    "checked_card",
                    services.get(childIndex).title
                ); curService = services.get(childIndex).title;
            }
        }
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
        constraint.visibility = View.INVISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
        constraint.visibility = View.VISIBLE
    }

    override fun showSnackBar(message: String) {
        Snackbar.make(constraint, message, Snackbar.LENGTH_SHORT)
            .show()
        presenter.getCardBalance(curCardCode)
    }

    override fun showSuccessDialog() {
        MaterialAlertDialogBuilder(context)
            .setTitle("Покупка услуги")
            .setMessage("Вы успешно приобрели услугу!")
            .setNegativeButton("ок", {_,_ -> mainActivity.setFragmentMain(FragmentNames.MainFragment, mainActivity)})
            .show()
    }

    private fun buyService(){
        if(curCardCode!="" && curService!=""){
            presenter.buyService(curCardCode,curService)
        }else{
            showSnackBar(getString(R.string.buy_service_error))
        }
    }

    override fun showCardBalance(balance: String) {
        moneyLabel.visibility = View.VISIBLE
        cardNumberLabel.visibility = View.VISIBLE
        if (balance != "") {
            moneyLabel.setText("Баланс: " + balance.toFloat().toLong())
        } else {
            moneyLabel.setText("Баланс: 0")
        }
        cardNumberLabel.setText(curCardCode)
    }
}