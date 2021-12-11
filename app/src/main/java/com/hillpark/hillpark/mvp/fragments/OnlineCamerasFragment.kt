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
import com.hillpark.hillpark.adapters.CamerasAdapter
import com.hillpark.hillpark.constants.FragmentNames
import com.hillpark.hillpark.model.CameraUrl
import com.hillpark.hillpark.mvp.MainActivity
import com.hillpark.hillpark.mvp.presenters.OnlineCamerasPresenter
import com.hillpark.hillpark.mvp.view.OnlineCamerasView
import kotlinx.android.synthetic.main.fragment_online_cameras.*

class OnlineCamerasFragment : MvpAppCompatFragment(), OnlineCamerasView {

    @InjectPresenter
    lateinit var presenter: OnlineCamerasPresenter
    private lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_online_cameras, container, false)
    }

    override fun onStart() {
        super.onStart()
        mainActivity = activity as MainActivity
    }

    override fun onResume() {
        super.onResume()
        mainActivity.showMenuActBar("Онлайн камеры", mainActivity)
        mainActivity.hideBottomBar()
        presenter.loadCameras()
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

    override fun showCameras(list: List<CameraUrl>) {
        camerasRecycler.layoutManager = LinearLayoutManager(context)
        camerasRecycler.adapter = CamerasAdapter(list, this)
    }

    fun cameraClicked(url: String){
        mainActivity.setFragmentWithParam(FragmentNames.CameraViewFragment, mainActivity, url)
    }
}