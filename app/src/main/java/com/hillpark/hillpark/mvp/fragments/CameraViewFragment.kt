package com.hillpark.hillpark.mvp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.hillpark.hillparkApp.R
import com.hillpark.hillpark.constants.AppConst
import com.hillpark.hillpark.mvp.MainActivity
import kotlinx.android.synthetic.main.fragment_camera_view.*

class CameraViewFragment : MvpAppCompatFragment() {

    private lateinit var url: String
    private lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        url = arguments!!.getString(AppConst.URL_KEY, "")
        return inflater.inflate(R.layout.fragment_camera_view, container, false)
    }

    override fun onStart() {
        super.onStart()
        mainActivity = activity as MainActivity
        startView()
    }

    override fun onResume() {
        super.onResume()
        mainActivity.hideBottomBar()
    }

    private fun startView(){
        webView.loadUrl(url)
        webView.settings.javaScriptEnabled = true
    }

    companion object{
        fun newInstance(url: String) : CameraViewFragment{
            val fragment = CameraViewFragment()
            val args = Bundle()
            args.putString(AppConst.URL_KEY, url)
            fragment.arguments = args
            return fragment
        }
    }
}