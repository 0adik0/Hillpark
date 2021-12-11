package com.hillpark.hillpark.mvp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.google.android.material.snackbar.Snackbar
import com.hillpark.hillparkApp.R
import com.hillpark.hillpark.adapters.TariffPricesAdapter
import com.hillpark.hillpark.constants.AppConst
import com.hillpark.hillpark.model.TariffItemPriceModel
import com.hillpark.hillpark.mvp.MainActivity
import com.hillpark.hillpark.mvp.presenters.TariffPricesPresenter
import com.hillpark.hillpark.mvp.view.TariffPricesView
import kotlinx.android.synthetic.main.fragment_tariff_price_list.*

class TariffPricesFragment : MvpAppCompatFragment(), TariffPricesView {

    private lateinit var mainActivity : MainActivity
    private lateinit var tariffId: String
    private lateinit var actionTitle: String
    @InjectPresenter
    lateinit var presenter: TariffPricesPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tariffId = arguments!!.getString(AppConst.TARIFF_ID, "")
        actionTitle = arguments!!.getString(AppConst.TARIFF_PRICES_TITLE, "")
        return inflater.inflate(R.layout.fragment_tariff_price_list, container, false)
    }

    override fun onStart() {
        super.onStart()
        mainActivity = activity as MainActivity
        mainActivity.showActBar(actionTitle)
    }

    override fun onResume() {
        super.onResume()
        mainActivity.showActBar(actionTitle)
        mainActivity.hideBottomBar()
        presenter.loadTariffPrices(tariffId)
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showSnackbar(message: String) {
        Snackbar.make(root,message, Snackbar.LENGTH_SHORT).show()
    }

    override fun showTariffPrices(list: List<TariffItemPriceModel>) {
        tariffPricesRecycler.layoutManager = LinearLayoutManager(context)
        tariffPricesRecycler.adapter = TariffPricesAdapter(list)
    }

    companion object{
        fun newInstance(id: String, title: String) : TariffPricesFragment{
            val fragment = TariffPricesFragment()
            val args = Bundle()
            args.putString(AppConst.TARIFF_ID, id)
            args.putString(AppConst.TARIFF_PRICES_TITLE, title)
            fragment.arguments = args
            return fragment
        }
    }
}