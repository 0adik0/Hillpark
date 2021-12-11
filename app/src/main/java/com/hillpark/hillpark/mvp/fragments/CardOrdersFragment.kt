package com.hillpark.hillpark.mvp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.google.android.material.snackbar.Snackbar
import com.hillpark.hillpark.adapters.CardOrdersAdapter
import com.hillpark.hillpark.constants.AppConst
import com.hillpark.hillpark.model.CardOrder
import com.hillpark.hillpark.mvp.MainActivity
import com.hillpark.hillpark.mvp.presenters.CardOrdersPresenter
import com.hillpark.hillpark.mvp.view.CardOrdersView
import com.hillpark.hillparkApp.R
import kotlinx.android.synthetic.main.fragment_card_orders.*

class CardOrdersFragment : MvpAppCompatFragment(), CardOrdersView {

    @InjectPresenter
    lateinit var presenter: CardOrdersPresenter
    lateinit var mainActivity: MainActivity
    private lateinit var cardCode: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cardCode = arguments!!.getString(AppConst.CARD_CODE_KEY, "")
        return inflater.inflate(R.layout.fragment_card_orders, container, false)
    }

    override fun onStart() {
        super.onStart()
        mainActivity = activity as MainActivity
    }

    override fun onResume() {
        super.onResume()
        mainActivity.showCardOrdersActBar(cardCode, mainActivity)
        presenter.loadOrders(cardCode)
    }

    override fun showOrders(list: List<CardOrder>) {
        cardOrdersRecycler.layoutManager = LinearLayoutManager(context)
        cardOrdersRecycler.adapter = CardOrdersAdapter(list)
    }

    override fun hideLoading() {
        constraint.visibility = View.VISIBLE
        progressBar.visibility = View.INVISIBLE
    }

    override fun showLoading() {
        constraint.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE
    }

    override fun showSnackbar(message: String) {
        Snackbar.make(root, message, Snackbar.LENGTH_SHORT).show()
    }

    companion object{
        fun newInstance(code: String) : CardOrdersFragment{
            val args = Bundle()
            val fragment = CardOrdersFragment()
            args.putString(AppConst.CARD_CODE_KEY, code)
            fragment.arguments = args
            return fragment
        }
    }
}