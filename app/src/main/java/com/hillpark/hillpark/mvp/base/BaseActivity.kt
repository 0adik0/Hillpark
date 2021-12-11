package ru.avtoapp.partner.base.mvp

import android.view.View
import android.view.View.*
import android.widget.ImageView
import android.widget.TextView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.hillpark.hillparkApp.R
import com.hillpark.hillpark.constants.AppConst
import com.hillpark.hillpark.constants.FragmentNames
import com.hillpark.hillpark.mvp.MainActivity
import com.hillpark.hillpark.mvp.fragments.*
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.activity_main.*



abstract class BaseActivity : MvpAppCompatActivity() {

    fun showSnackBar(message: String, view: View){
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
            .show()
    }

    fun showLoading(view: View){
        view.visibility = VISIBLE
    }

    fun hideLoading(view: View){
        view.visibility = INVISIBLE
    }

    fun setFragment(name: String){
        when(name){
            FragmentNames.LoginFragment -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.container,
                    LoginFragment()
                ).addToBackStack(null).commit()
                appBarAuth.visibility = GONE
            }
            FragmentNames.RegistrationFragment -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.container,
                    RegistrationFragment()
                ).addToBackStack(null).commit()
                appBarAuth.visibility = VISIBLE
                val title = appBarAuth.findViewById(R.id.actionTitle) as TextView
                val btn = appBarAuth.findViewById(R.id.backBtn) as View
                title.setText(R.string.register_title)
                btn.setOnClickListener { supportFragmentManager.popBackStack(); appBarAuth.visibility = GONE}
            }
        }
    }

    fun setFragmentMain(name: String, activity: MainActivity){
        when(name){
            FragmentNames.MainFragment -> {
                    supportFragmentManager.beginTransaction().replace(
                    R.id.mainContainer,
                    MainFragment()
                ).commit()
                appBar.visibility = INVISIBLE
                activity.hideNavigationBar()
                activity.showBottomBar(AppConst.MAIN_NAVIGATION_BUTTON)
                appBar.visibility = GONE
                showMenuActBar("", activity)
            }
            FragmentNames.AddMoneyToCardFragment -> {
                    supportFragmentManager.beginTransaction().replace(
                    R.id.mainContainer,
                    AddMoneyToCardFragment()
                ).addToBackStack(null).commit()
                activity.hideBottomBar()
                activity.hideNavigationBar()
                appBar.visibility = GONE
                showActBar(getString(R.string.money_to_card_bar_title))
            }
            FragmentNames.BuyServiceFragment -> {
                    supportFragmentManager.beginTransaction().replace(
                    R.id.mainContainer,
                    BuyServiceFragment()
                ).addToBackStack(null).commit()
                activity.hideBottomBar()
                activity.hideNavigationBar()
                appBar.visibility = GONE
                showActBar(getString(R.string.buy_service_bar_title))
            }
            FragmentNames.NotificationsFragment -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.mainContainer,
                    NotificationsFragment()
                ).commit()
                activity.showBottomBar(AppConst.NOTIFICATION_NAVIGATION_BUTTON)
                appBar.visibility = GONE
                showMenuActBar(getString(R.string.notifications_app_bar_title), activity)
                activity.hideNavigationBar()
            }
            FragmentNames.FragmentHistory -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.mainContainer,
                    HistoryFragment()
                ).addToBackStack(null).commit()
                activity.hideBottomBar()
                activity.hideNavigationBar()
                appBar.visibility = GONE
                showActBar(getString(R.string.history_app_bar_title))
            }
            FragmentNames.FragmentCardHistory -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.mainContainer,
                    CardHistoryFragment()
                ).addToBackStack(null).commit()
                activity.hideBottomBar()
                activity.hideNavigationBar()
                appBar.visibility = GONE
                showActBar(getString(R.string.history_app_bar_title))  // @todo карта пофиксить
            }
            FragmentNames.FragmentProfile -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.mainContainer,
                    ProfileFragment()
                ).addToBackStack(null).commit()
                activity.hideBottomBar()
                activity.hideNavigationBar()
                appBar.visibility = GONE
                showMenuActBar(getString(R.string.profile_app_bar_title), activity)
            }
            FragmentNames.FragmentContacts -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.mainContainer,
                    ContactsFragment()
                ).addToBackStack(null).commit()
                activity.hideBottomBar()
                activity.hideNavigationBar()
                appBar.visibility = GONE
                showMenuActBar(getString(R.string.contacts_app_bar_title), activity)
            }
            FragmentNames.FragmentMyCards -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.mainContainer,
                    MyCardsFragment()
                ).addToBackStack(null).commit()
                activity.hideBottomBar()
                activity.hideNavigationBar()
                appBar.visibility = GONE
                showMenuActBar(getString(R.string.my_cards_app_bar_title), activity)
            }
            FragmentNames.FragmentCardEdit -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.mainContainer,
                    CardEditFragment()
                ).addToBackStack(null).commit()
                activity.hideBottomBar()
                activity.hideNavigationBar()
                appBar.visibility = GONE
                showActBar(getString(R.string.history_app_bar_title))  // @todo карта пофиксить
            }
            FragmentNames.MyOrdersFragment -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.mainContainer,
                    MyOrdersFragment()
                ).commit()
                appBar.visibility = GONE
                showMenuActBar(getString(R.string.my_orders_app_bar_title), activity)
            }
            FragmentNames.TariffsFragment -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.mainContainer,
                    TariffsFragment()
                ).addToBackStack(null).commit()
                activity.hideNavigationBar()
                appBar.visibility = GONE
                showActBar(getString(R.string.tariffs_app_bar_title))
            }
            FragmentNames.OnlineCameras -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.mainContainer,
                    OnlineCamerasFragment()
                ).addToBackStack(null).commit()
                activity.hideNavigationBar()
                appBar.visibility = GONE
                showActBar("Онлайн камеры")
            }
            FragmentNames.WeatherFragment -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.mainContainer,
                    WeatherFragment()
                ).addToBackStack(null).commit()
                activity.hideNavigationBar()
                appBar.visibility = GONE
                showActBar("Информер погоды")
            }
            FragmentNames.ActionsFragment -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.mainContainer,
                    ActionsFragment()
                ).addToBackStack(null).commit()
                activity.hideNavigationBar()
                appBar.visibility = GONE
                showActBar("АКЦИИ")
            }
        }
    }

    fun setPayFragment(name: String, activity: MainActivity, sum: Long, cardNum: String){
        supportFragmentManager.beginTransaction().replace(
            R.id.mainContainer,
            PayFragment.newInstance(sum, cardNum)
        ).addToBackStack(null).commit()
        activity.hideNavigationBar()
        appBar.visibility = GONE
    }

    fun setCardFragment(name: String, activity: MainActivity, code: String){
        when(name) {
            FragmentNames.FragmentCardHistory -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.mainContainer,
                    CardHistoryFragment.newInstance(code)
                ).addToBackStack(null).commit()
                activity.hideNavigationBar()
                appBar.visibility = GONE
                showActBar(code)
            }
            FragmentNames.CardOrdersFragment -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.mainContainer,
                    CardOrdersFragment.newInstance(code)
                ).commit()
                activity.hideNavigationBar()
                appBar.visibility = GONE
                showCardOrdersActBar(code, activity)
            }
        }
    }

    fun setFragmentWithParam(name: String, activity: MainActivity, param1: String){
        when(name){
            FragmentNames.CameraViewFragment -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.mainContainer,
                    CameraViewFragment.newInstance(param1)
                ).addToBackStack(null).commit()
                activity.hideNavigationBar()
                appBar.visibility = GONE
            }
        }
    }

    fun setFragmentWithTwoParam(name: String, activity: MainActivity, param1: String, param2: String){
        when(name){
            FragmentNames.TariffPricesFragment -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.mainContainer,
                    TariffPricesFragment.newInstance(param1, param2)
                ).addToBackStack(null).commit()
                activity.hideNavigationBar()
                appBar.visibility = GONE
            }
            FragmentNames.FragmentCardEdit -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.mainContainer,
                    CardEditFragment.newInstance(param1, param2)
                ).addToBackStack(null).commit()
                activity.hideNavigationBar()
                appBar.visibility = GONE
                showActBar(param1)
            }
        }
    }

    fun showActBar(title: String){
        appBar.visibility = VISIBLE
        val actionTitle = appBar.findViewById(R.id.actionTitle) as TextView
        actionTitle.setText(title)
        val backBtn = appBar.findViewById(R.id.backBtn) as View
        val iconBtn = appBar.findViewById(R.id.backImage) as ImageView
        iconBtn.setImageResource(R.drawable.ic_back_arrow)
        backBtn.setOnClickListener { supportFragmentManager.popBackStack();}
    }

    fun showCardOrdersActBar(title: String, mainActivity: MainActivity){
        appBar.visibility = VISIBLE
        val actionTitle = appBar.findViewById(R.id.actionTitle) as TextView
        actionTitle.setText(title)
        val backBtn = appBar.findViewById(R.id.backBtn) as View
        val iconBtn = appBar.findViewById(R.id.backImage) as ImageView
        iconBtn.setImageResource(R.drawable.ic_back_arrow)
        backBtn.setOnClickListener { setFragmentMain(FragmentNames.MyOrdersFragment, mainActivity)}
    }

    fun showMenuActBar(title: String, activity: MainActivity){
        appBar.visibility = VISIBLE
        val actionTitle = appBar.findViewById(R.id.actionTitle) as TextView
        actionTitle.setText(title)
        val iconBtn = appBar.findViewById(R.id.backImage) as ImageView
        val backBtn = appBar.findViewById(R.id.backBtn) as View
        iconBtn.setImageResource(R.drawable.ic_menu)
        backBtn.setOnClickListener { activity.showNavigationBar(); }
    }

    fun hideAppBar(activity: MainActivity){
        activity.hideBottomBar()
    }

    protected abstract fun showSnackbar(message: String)

    protected abstract fun showLoading()

    protected abstract fun hideLoading()
}