package com.hillpark.hillpark.mvp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.hillpark.hillparkApp.R
import com.hillpark.hillpark.constants.AppConst
import com.hillpark.hillpark.constants.FragmentNames
import com.hillpark.hillpark.mvp.MainActivity
import com.hillpark.hillpark.mvp.presenters.PayPresenter
import com.hillpark.hillpark.mvp.view.PayFragmentView
import kotlinx.android.synthetic.main.fragment_pay.*


class PayFragment : MvpAppCompatFragment(), PayFragmentView {

    @InjectPresenter
    lateinit var presenter: PayPresenter
    private var payUrl = ""
    private var sum: Long = 0
    private lateinit var card: String
    private lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sum = arguments!!.getLong(AppConst.SUM_KEY)
        card = arguments!!.getString(AppConst.CARD_KEY)!!
        return inflater.inflate(R.layout.fragment_pay, container, false)
    }

    override fun onStart() {
        super.onStart()
        mainActivity = activity as MainActivity
        progressBar.visibility = View.VISIBLE
        presenter.loadUrl(sum,card)
    }

    override fun showPaymentWindow(url: String) {
        payUrl = url
        WebView.getSettings().setLoadWithOverviewMode(true)
        WebView.loadUrl(payUrl)
        WebView.settings.javaScriptEnabled = true
        WebView.setWebViewClient(object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progressBar.visibility = View.GONE
            }

        })
        WebView.addJavascriptInterface(object : Any() {
            @JavascriptInterface
            public fun Close(){
                Thread(Runnable {
                    mainActivity.runOnUiThread(java.lang.Runnable {
                        goMain()
                    })
                }).start()
            }
        }, "WebView")

    }

    private fun goMain(){
        mainActivity.setFragmentMain(FragmentNames.MainFragment, mainActivity)
    }

    companion object{
        fun newInstance(sum: Long, card: String) : PayFragment{
            val fragment = PayFragment()
            val args = Bundle()
            args.putLong(AppConst.SUM_KEY, sum)
            args.putString(AppConst.CARD_KEY, card)
            fragment.arguments = args
            return fragment
        }
    }

}