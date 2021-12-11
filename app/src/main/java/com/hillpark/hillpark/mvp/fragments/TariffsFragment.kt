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
import com.hillpark.hillpark.adapters.TariffsCategoriesAdapter
import com.hillpark.hillpark.constants.FragmentNames
import com.hillpark.hillpark.model.TariffCategoryModel
import com.hillpark.hillpark.mvp.MainActivity
import com.hillpark.hillpark.mvp.presenters.TariffPresenter
import com.hillpark.hillpark.mvp.view.TariffsView
import kotlinx.android.synthetic.main.fragment_tarifs.*

class TariffsFragment : MvpAppCompatFragment(), TariffsView {

    private lateinit var mainActivity : MainActivity
    @InjectPresenter
    lateinit var presenter: TariffPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tarifs, container, false)
    }

    override fun onStart() {
        super.onStart()
        mainActivity = activity as MainActivity
        tariffBuyServiceBtn.setOnClickListener { mainActivity.setFragmentMain(FragmentNames.BuyServiceFragment, mainActivity) }
        tariffAddToCardBtn.setOnClickListener { mainActivity.setFragmentMain(FragmentNames.AddMoneyToCardFragment, mainActivity) }
        mainActivity.showActBar(context!!.getString(R.string.tariffs_app_bar_title))
    }

    override fun onResume() {
        super.onResume()
        mainActivity.showMenuActBar(context!!.getString(R.string.tariffs_app_bar_title), mainActivity)
        mainActivity.hideBottomBar()
        presenter.loadTariffCategories()
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
        Snackbar.make(root,message, Snackbar.LENGTH_SHORT).show()
    }

    override fun showTariffCategories(list: List<TariffCategoryModel>) {
        tariffCategoriesRecycler.layoutManager = LinearLayoutManager(context)
        tariffCategoriesRecycler.adapter = TariffsCategoriesAdapter(list, this)
    }

    fun tariffClicked(id: String, title: String){
        mainActivity.setFragmentWithTwoParam(FragmentNames.TariffPricesFragment, mainActivity, id, title)
    }
}