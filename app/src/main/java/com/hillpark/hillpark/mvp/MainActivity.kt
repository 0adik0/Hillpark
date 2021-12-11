package com.hillpark.hillpark.mvp

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.hillpark.hillpark.constants.AppConst
import com.hillpark.hillpark.constants.FragmentNames
import com.hillpark.hillpark.mvp.view.MainView
import com.hillpark.hillparkApp.R
import kotlinx.android.synthetic.main.activity_main.*
import ru.avtoapp.partner.base.mvp.BaseActivity


class MainActivity : BaseActivity(), MainView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setFragmentMain(FragmentNames.MainFragment, this)
        bottom_navigation.setOnNavigationItemSelectedListener { item: MenuItem -> inBottomSelected(item.itemId) }
        drawer.setDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
            }

            override fun onDrawerClosed(drawerView: View) {
            }

            override fun onDrawerOpened(drawerView: View) {
            }

            override fun onDrawerStateChanged(newState: Int) {
                Log.e("state", newState.toString())
                if(newState==1)drawerLoad()
            }

        })
    }
    override fun showSnackbar(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideNavigationBar() {
        drawer.closeDrawer(GravityCompat.START)
    }

    override fun showNavigationBar() {
        drawer.openDrawer(GravityCompat.START)

        val first_name = getSharedPreferences(AppConst.APP_PREFERENCES, MODE_PRIVATE).getString(AppConst.FIRST_NAME, "")
        val last_name = getSharedPreferences(AppConst.APP_PREFERENCES, MODE_PRIVATE).getString(AppConst.LAST_NAME, "")

        nameNavField.text = last_name + "\n" + first_name
        myOrdersNavBtn.setOnClickListener { hideNavigationBar();setFragmentMain(FragmentNames.MyOrdersFragment, this) }
        historyNavBtn.setOnClickListener { hideNavigationBar();setFragmentMain(FragmentNames.FragmentHistory, this) }
        profileNavBtn.setOnClickListener { hideNavigationBar();setFragmentMain(FragmentNames.FragmentProfile, this) }
        myCardsNavBtn.setOnClickListener { hideNavigationBar();setFragmentMain(FragmentNames.FragmentMyCards, this) }
        contactsNavBtn.setOnClickListener { hideNavigationBar();setFragmentMain(FragmentNames.FragmentContacts, this) }
        camerasNavBtn.setOnClickListener { hideNavigationBar();setFragmentMain(FragmentNames.OnlineCameras, this) }
        informerNavBtn.setOnClickListener { hideNavigationBar();setFragmentMain(FragmentNames.WeatherFragment, this)  }
        actionsNavBtn.setOnClickListener { hideNavigationBar();setFragmentMain(FragmentNames.ActionsFragment, this) }
        exitNavBtn.setOnClickListener { hideNavigationBar();finishAffinity()}
        closeNavBtn.setOnClickListener { hideNavigationBar() }

    }

    override fun showBottomBar(checkedPosition: String) {
        bottom_navigation.visibility = View.VISIBLE
        when(checkedPosition){
            AppConst.MAIN_NAVIGATION_BUTTON -> bottom_navigation.menu.findItem(R.id.main_screen_button).isChecked = true
            AppConst.BASKET_NAVIGATION_BUTTON -> bottom_navigation.menu.findItem(R.id.basket_screen_button).isChecked = true
            AppConst.NOTIFICATION_NAVIGATION_BUTTON -> bottom_navigation.menu.findItem(R.id.notify_screen_button).isChecked = true
        }
    }

    override fun hideBottomBar() {
        bottom_navigation.visibility = View.GONE
    }

    override fun onBackPressed() {
        supportFragmentManager.popBackStack()
    }

    private fun inBottomSelected(id: Int) : Boolean{
        when(id){
            R.id.main_screen_button -> setFragmentMain(FragmentNames.MainFragment, this)
            R.id.basket_screen_button -> setFragmentMain(FragmentNames.MyOrdersFragment, this)
            R.id.notify_screen_button -> setFragmentMain(FragmentNames.NotificationsFragment, this)
        }
        return true
    }

    private fun drawerLoad(){
        val first_name = getSharedPreferences(AppConst.APP_PREFERENCES, MODE_PRIVATE).getString(AppConst.FIRST_NAME, "")
        val last_name = getSharedPreferences(AppConst.APP_PREFERENCES, MODE_PRIVATE).getString(AppConst.LAST_NAME, "")

        nameNavField.text = last_name + "\n" + first_name
        myOrdersNavBtn.setOnClickListener { hideNavigationBar();setFragmentMain(FragmentNames.MyOrdersFragment, this) }
        historyNavBtn.setOnClickListener { hideNavigationBar();setFragmentMain(FragmentNames.FragmentHistory, this) }
        profileNavBtn.setOnClickListener { hideNavigationBar();setFragmentMain(FragmentNames.FragmentProfile, this) }
        myCardsNavBtn.setOnClickListener { hideNavigationBar();setFragmentMain(FragmentNames.FragmentMyCards, this) }
        contactsNavBtn.setOnClickListener { hideNavigationBar();setFragmentMain(FragmentNames.FragmentContacts, this) }
        camerasNavBtn.setOnClickListener { hideNavigationBar();setFragmentMain(FragmentNames.OnlineCameras, this) }
        informerNavBtn.setOnClickListener { hideNavigationBar();setFragmentMain(FragmentNames.WeatherFragment, this)  }
        actionsNavBtn.setOnClickListener { hideNavigationBar();setFragmentMain(FragmentNames.ActionsFragment, this) }
        exitNavBtn.setOnClickListener { hideNavigationBar();finishAffinity()}
        closeNavBtn.setOnClickListener { hideNavigationBar() }
    }

    override fun applyOverrideConfiguration(overrideConfiguration: Configuration) {
        if (Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT < 25) {
            overrideConfiguration.uiMode =
                overrideConfiguration.uiMode and Configuration.UI_MODE_NIGHT_MASK.inv()
        }
        super.applyOverrideConfiguration(overrideConfiguration)
    }
}