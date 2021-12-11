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
import com.hillpark.hillpark.adapters.CardHistoryAdapter
import com.hillpark.hillpark.constants.AppConst
import com.hillpark.hillpark.model.CardOperation
import com.hillpark.hillpark.mvp.MainActivity
import com.hillpark.hillpark.mvp.presenters.CardHistoryPresenter
import com.hillpark.hillpark.mvp.view.CardHistoryView
import kotlinx.android.synthetic.main.fragment_card_history.*

class CardHistoryFragment : MvpAppCompatFragment(), CardHistoryView {

    @InjectPresenter
    lateinit var presenter: CardHistoryPresenter
    lateinit var mainActivity: MainActivity
    private lateinit var cardCode: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cardCode = arguments!!.getString(AppConst.CARD_CODE_KEY, "")
        return inflater.inflate(R.layout.fragment_card_history, container, false)
    }

    override fun onStart() {
        super.onStart()
        mainActivity = activity as MainActivity
    }

    override fun onResume() {
        super.onResume()
        mainActivity.showActBar(cardCode)
        presenter.getOperations(cardCode)
    }

    override fun showHistory(list: List<CardOperation>) {
        cardHistoryRecycler.layoutManager = LinearLayoutManager(context)
        cardHistoryRecycler.adapter = CardHistoryAdapter(list)
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
        fun newInstance(code: String) : CardHistoryFragment{
            val args = Bundle()
            val fragment = CardHistoryFragment()
            args.putString(AppConst.CARD_CODE_KEY, code)
            fragment.arguments = args
            return fragment
        }
    }
}