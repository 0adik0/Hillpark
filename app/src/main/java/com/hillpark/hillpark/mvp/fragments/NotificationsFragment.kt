package com.hillpark.hillpark.mvp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hillpark.hillparkApp.R
import com.hillpark.hillpark.adapters.NotificationAdapter
import com.hillpark.hillpark.constants.AppConst
import com.hillpark.hillpark.mvp.MainActivity
import kotlinx.android.synthetic.main.fragment_notifications.*

class NotificationsFragment : Fragment() {

    private lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notifications, container, false)
    }

    override fun onStart() {
        super.onStart()
        mainActivity = activity as MainActivity
    }

    override fun onResume() {
        super.onResume()
        newNotificationsRecycler.layoutManager = LinearLayoutManager(context)
        readedNotificationsRecycler.layoutManager = LinearLayoutManager(context)
        newNotificationsRecycler.adapter = NotificationAdapter()
        readedNotificationsRecycler.adapter = NotificationAdapter()
        mainActivity.showMenuActBar(getString(R.string.notifications_app_bar_title), mainActivity)
        mainActivity.showBottomBar(AppConst.NOTIFICATION_NAVIGATION_BUTTON)
    }
}