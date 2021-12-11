package com.hillpark.hillpark.mvp.fragments

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.hillpark.hillparkApp.R
import com.hillpark.hillpark.adapters.MyCardsAdapter
import com.hillpark.hillpark.constants.FragmentNames
import com.hillpark.hillpark.model.Card
import com.hillpark.hillpark.mvp.MainActivity
import com.hillpark.hillpark.mvp.presenters.MyCardsPresenter
import com.hillpark.hillpark.mvp.view.MyCardsView
import kotlinx.android.synthetic.main.fragment_my_cards.*
import kotlinx.android.synthetic.main.item_add_card.*

class MyCardsFragment : MvpAppCompatFragment(), MyCardsView {

    @InjectPresenter
    lateinit var presenter : MyCardsPresenter
    private lateinit var mainActivity : MainActivity
    private lateinit var dialog : AlertDialog
    private lateinit var cardsAdapter: MyCardsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_cards, container, false)
    }

    override fun onStart() {
        super.onStart()
        mainActivity = activity as MainActivity
    }

    override fun onResume() {
        super.onResume()
        mainActivity.showMenuActBar(getString(R.string.my_cards_app_bar_title), mainActivity)
        addCardBtn.setOnClickListener { openAddCardDialog() }
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

    override fun showSnackBar(message: String) {
        Snackbar.make(constraint, message, Snackbar.LENGTH_SHORT)
            .show()
    }

    override fun showCards(list: List<Card>) {
        val listNum = ArrayList(list)
        cardsAdapter = MyCardsAdapter(listNum, this)
        cardRecycler.layoutManager = LinearLayoutManager(context)
        cardRecycler.adapter = cardsAdapter
    }

    override fun cardClicked(name: String, code: String) {
        mainActivity.setFragmentWithTwoParam(FragmentNames.FragmentCardEdit,mainActivity, name, code)
    }

    private fun openAddCardDialog(){
        val colorDrawable = ColorDrawable(context!!.resources.getColor(R.color.colorPrimary))
        dialog = MaterialAlertDialogBuilder(context)
            .setView(this.layoutInflater.inflate(R.layout.item_add_card, null))
            .setBackground(colorDrawable)
            .setPositiveButton(R.string.dialog_ok) {_,_ -> presenter.addCard(dialog.currentCardEditText.text.toString())}
            .setNegativeButton(R.string.dialog_cancel) {_,_ ->}
            .create()
        dialog.show()
    }

    fun openRemoveDialog(code: String, name: String){
        val colorDrawable = ColorDrawable(context!!.resources.getColor(R.color.colorPrimary))
        dialog = MaterialAlertDialogBuilder(context)
            .setBackground(colorDrawable)
            .setTitle("Удаление карты")
            .setMessage("Удалить карту " + name + " ?")
            .setPositiveButton(R.string.dialog_ok) {_,_ -> presenter.deleteCard(code)}
            .setNegativeButton(R.string.dialog_cancel) {_,_ ->}
            .create()
        dialog.show()
    }

    override fun removeCard() {
        cardsAdapter.removeCardFromList()
    }
}