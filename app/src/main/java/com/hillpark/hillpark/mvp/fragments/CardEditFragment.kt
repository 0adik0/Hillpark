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
import com.hillpark.hillpark.adapters.CardInfoAdapter
import com.hillpark.hillpark.constants.AppConst
import com.hillpark.hillpark.model.CardInfo
import com.hillpark.hillpark.mvp.presenters.CardEditPresenter
import com.hillpark.hillpark.mvp.view.CardEditView
import kotlinx.android.synthetic.main.fragment_card_edit.*
import ru.avtoapp.partner.base.mvp.BaseActivity

class CardEditFragment : MvpAppCompatFragment(), CardEditView {

    @InjectPresenter
    lateinit var presenter: CardEditPresenter
    private lateinit var mainActivity: BaseActivity

    private lateinit var cardCode: String
    private lateinit var cardName: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = activity as BaseActivity
        cardName = arguments!!.getString(AppConst.CARD_NAME_KEY)!!
        cardCode = arguments!!.getString(AppConst.CARD_CODE_KEY)!!
        return inflater.inflate(R.layout.fragment_card_edit, container, false)
    }

    override fun onResume() {
        super.onResume()
        presenter.loadCardInfo(cardCode)
        saveBtn.setOnClickListener { if(cardNameEditText.text.toString()!="" && cardNameEditText.text.toString()!=" ")presenter.editCardName(cardCode ,cardNameEditText.text.toString()) else showSnackBar("Введите имя карты")}
    }

    override fun showCardInfo(cardInfo: CardInfo) {
        cardInfoRecycler.layoutManager = LinearLayoutManager(context)
        cardInfoRecycler.adapter = CardInfoAdapter(cardInfo)
     //   mainActivity.showActBar(///) @todo card fix
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
    }

    override fun reload() {
        presenter.loadCardInfo(cardCode)
    }

    companion object{
        fun newInstance(name: String, code: String) : CardEditFragment{
            val args = Bundle()
            val fragment = CardEditFragment()
            args.putString(AppConst.CARD_CODE_KEY, code)
            args.putString(AppConst.CARD_NAME_KEY, name)
            fragment.arguments = args
            return fragment
        }
    }
}