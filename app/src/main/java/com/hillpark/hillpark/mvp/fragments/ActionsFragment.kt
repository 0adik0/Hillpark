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
import com.hillpark.hillpark.adapters.ActionsAdapter
import com.hillpark.hillpark.constants.FragmentNames
import com.hillpark.hillpark.model.ActionModel
import com.hillpark.hillpark.mvp.MainActivity
import com.hillpark.hillpark.mvp.presenters.ActionsPresenter
import com.hillpark.hillpark.mvp.view.ActionsView
import kotlinx.android.synthetic.main.fragment_sale.*

class ActionsFragment : MvpAppCompatFragment(), ActionsView {

    @InjectPresenter
    lateinit var presenter: ActionsPresenter
    private lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sale, container, false)
    }

    override fun onStart() {
        super.onStart()
        mainActivity = activity as MainActivity
    }

    override fun onResume() {
        super.onResume()
        mainActivity.showMenuActBar("АКЦИИ", mainActivity)
        mainActivity.hideBottomBar()
        presenter.loadActions()
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

    override fun showActions(list: ArrayList<ActionModel>) {
        var ctr = 0
        while (ctr<list.size){
            if(list[ctr].widget!="INFO_WIDGET") {
                list.removeAt(ctr)
                ctr = 0
            }
            ctr++
        }
        actionsRecycler.layoutManager = LinearLayoutManager(context)
        actionsRecycler.adapter = ActionsAdapter(list, this)
    }

    override fun imageClicked(url: String) {
        mainActivity.setFragmentWithParam(FragmentNames.CameraViewFragment, mainActivity, url)
    }

}