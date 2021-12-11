package com.hillpark.hillpark.mvp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.google.android.material.snackbar.Snackbar
import com.hillpark.hillpark.adapters.ChooseCardHistoryAdapter
import com.hillpark.hillpark.adapters.ChooseCardOrdersAdapter
import com.hillpark.hillpark.constants.AppConst
import com.hillpark.hillparkApp.R
import com.hillpark.hillpark.constants.FragmentNames
import com.hillpark.hillpark.model.Card
import com.hillpark.hillpark.mvp.MainActivity
import com.hillpark.hillpark.mvp.presenters.MyOrdersPresenter
import com.hillpark.hillpark.mvp.view.MyOrdersView
import kotlinx.android.synthetic.main.fragment_my_orders.*

class MyOrdersFragment : MvpAppCompatFragment(), MyOrdersView {

    private lateinit var mainActivity: MainActivity
    @InjectPresenter
    lateinit var presenter: MyOrdersPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_orders, container, false)
    }
    override fun onStart() {
        super.onStart()
        mainActivity = activity as MainActivity
    }

    override fun onResume() {
        super.onResume()
        mainActivity.showMenuActBar(getString(R.string.my_orders_app_bar_title), mainActivity)
        mainActivity.showBottomBar(AppConst.BASKET_NAVIGATION_BUTTON)
        presenter.loadCards()
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
        constraint.visibility = View.INVISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
        constraint.visibility = View.VISIBLE
    }

    override fun showSnackbar(message: String) {
        Snackbar.make(root, message, Snackbar.LENGTH_SHORT)
            .show()
    }

    override fun showCards(list: List<Card>) {
        chooseCardOrdersRecycler.layoutManager = LinearLayoutManager(context)
        chooseCardOrdersRecycler.adapter = ChooseCardOrdersAdapter(list, this)
    }

    override fun cardClicked(code: String) {
        mainActivity.setCardFragment(FragmentNames.CardOrdersFragment,mainActivity, code) // @todo orders card
    }
}