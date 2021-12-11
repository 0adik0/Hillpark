package com.hillpark.hillpark.mvp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.hillpark.hillparkApp.R
import com.hillpark.hillpark.adapters.CardAdapter
import com.hillpark.hillpark.constants.FragmentNames
import com.hillpark.hillpark.model.Card
import com.hillpark.hillpark.model.SingleCheckGroup
import com.hillpark.hillpark.mvp.MainActivity
import com.hillpark.hillpark.mvp.presenters.AddMoneyToCardPresenter
import com.hillpark.hillpark.mvp.view.AddMoneyToCardView
import kotlinx.android.synthetic.main.fragment_add_money_to_card.*

class AddMoneyToCardFragment : MvpAppCompatFragment(), AddMoneyToCardView {

    @InjectPresenter
    lateinit var presenter : AddMoneyToCardPresenter
    private var checkedCard: String = ""
    private var checkedSum: Long = 0
    private lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_money_to_card, container, false)
    }

    override fun onResume() {
        super.onResume()
        mainActivity.showActBar(getString(R.string.money_to_card_bar_title))
        mainActivity.hideAppBar(mainActivity)
        sumInputEditText.setText("", TextView.BufferType.EDITABLE)
        buttonLabel.setText("ОПЛАТИТЬ")
        moneyLabel.visibility = View.INVISIBLE
        cardNumberLabel.visibility = View.INVISIBLE
        checkedSum = 0
        checkedCard = ""
    }

    override fun onStart() {
        super.onStart()
        mainActivity = activity as MainActivity
        presenter.loadCards()
        sumInputEditText.addTextChangedListener { text ->
            if(text.toString()!="") {
                buttonLabel.setText("ОПЛАТИТЬ " + text.toString() + " " + "\u20BD")
                checkedSum = text.toString().toLong()
            }else{
                buttonLabel.setText("ОПЛАТИТЬ")
                checkedSum = 0
            }
        }

        payBtn.setOnClickListener {
            if(checkedSum>0 && checkedCard!=""){
            mainActivity.setPayFragment(FragmentNames.PayFragment, mainActivity, checkedSum, checkedCard)
            }}



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
        adapter.setChildClickListener { v, checked, group, childIndex ->  checkedCard = cards.get(childIndex).code; presenter.getCardBalance(cards.get(childIndex).code); adapter.onGroupClick(0);}
    }

    override fun showCardBalance(balance: String) {
            moneyLabel.visibility = View.VISIBLE
            cardNumberLabel.visibility = View.VISIBLE
            if (balance != "") {
                moneyLabel.setText("Баланс: " + balance.toFloat().toLong())
            } else {
                moneyLabel.setText("Баланс: 0")
            }
            cardNumberLabel.setText(checkedCard)
        }

    override fun showLoading() {
        constraint.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading(error: Boolean) {
        if(!error) {
            constraint.visibility = View.VISIBLE
        }
        progressBar.visibility = View.GONE
    }
}