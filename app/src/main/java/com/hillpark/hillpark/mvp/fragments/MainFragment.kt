package com.hillpark.hillpark.mvp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.hillpark.hillpark.constants.AppConst
import com.hillpark.hillpark.constants.FragmentNames
import com.hillpark.hillpark.model.SliderServerItem
import com.hillpark.hillpark.mvp.MainActivity
import com.hillpark.hillpark.mvp.presenters.MainPresenter
import com.hillpark.hillpark.mvp.view.MainFragmentView
import kotlinx.android.synthetic.main.fragment_main.*
import com.smarteist.autoimageslider.SliderView
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.IndicatorAnimations
import android.graphics.Color
import com.hillpark.hillparkApp.R
import com.hillpark.hillpark.adapters.SliderAdapter
import com.hillpark.hillpark.model.SliderItem
import com.hillpark.hillpark.utils.DynClass


class MainFragment : MvpAppCompatFragment(), MainFragmentView {

    private lateinit var mainActivity: MainActivity
    @InjectPresenter
    lateinit var presenter: MainPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onStart() {
        super.onStart()
        mainActivity = activity as MainActivity
        addMoneyToCardBtn.setOnClickListener {  mainActivity.setFragmentMain(FragmentNames.AddMoneyToCardFragment, mainActivity)}
        buyServiceBtn.setOnClickListener { mainActivity.setFragmentMain(FragmentNames.BuyServiceFragment, mainActivity) }
        tariffsBtn.setOnClickListener { mainActivity.setFragmentMain(FragmentNames.TariffsFragment, mainActivity) }
        mainActivity.showBottomBar(checkedPosition = AppConst.MAIN_NAVIGATION_BUTTON)
        sliderProgress.visibility = View.VISIBLE

        if(DynClass.isSliderImageListInitialized()){
            sliderLoaded(DynClass.sliderImageList)
        }else{
            presenter.loadSlider()
        }
    }

    override fun onResume() {
        super.onResume()
        mainActivity.showMenuActBar("", mainActivity)
    }

    override fun sliderLoaded(list: ArrayList<SliderItem>) {

        var ctr = 0
        while (ctr<list.size){
            if(list[ctr].widget!="AD_WIDGET") {
                list.removeAt(ctr)
                ctr = 0
            }
            ctr++
        }
        val adapter = SliderAdapter(list, this)
        sliderProgress.visibility = View.GONE
        imageSlider.setSliderAdapter(adapter)

        imageSlider.setIndicatorAnimation(IndicatorAnimations.WORM)
        imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH)
        imageSlider.setIndicatorSelectedColor(Color.WHITE)
        imageSlider.setIndicatorUnselectedColor(Color.GRAY)
        imageSlider.setScrollTimeInSec(60)
        imageSlider.startAutoCycle()
    }

    override fun openUrl(url: String) {
        mainActivity.setFragmentWithParam(FragmentNames.CameraViewFragment, mainActivity, url)
    }
}