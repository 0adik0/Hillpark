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
import com.hillpark.hillpark.adapters.ChooseCardHistoryAdapter
import com.hillpark.hillpark.constants.FragmentNames
import com.hillpark.hillpark.model.Card
import com.hillpark.hillpark.mvp.MainActivity
import com.hillpark.hillpark.mvp.presenters.HistoryPresenter
import com.hillpark.hillpark.mvp.view.HistoryView
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragment : MvpAppCompatFragment(), HistoryView {

    @InjectPresenter
    lateinit var presenter: HistoryPresenter
    private lateinit var mainActivity : MainActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }
    override fun onStart() {
        super.onStart()
        mainActivity = activity as MainActivity
    }

    override fun onResume() {
        super.onResume()
        mainActivity.showActBar(getString(R.string.history_app_bar_title))
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
        chooseCardHistoryRecycler.layoutManager = LinearLayoutManager(context)
        chooseCardHistoryRecycler.adapter = ChooseCardHistoryAdapter(list, this)
    }

    override fun cardClicked(code: String) {
        mainActivity.setCardFragment(FragmentNames.FragmentCardHistory,mainActivity, code)
    }
}