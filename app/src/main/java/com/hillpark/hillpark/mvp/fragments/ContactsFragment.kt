package com.hillpark.hillpark.mvp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hillpark.hillparkApp.R
import com.hillpark.hillpark.mvp.MainActivity
import kotlinx.android.synthetic.main.fragment_contacts.*
import android.content.Intent
import android.net.Uri






class ContactsFragment : Fragment(){

    private lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contacts, container, false)
    }

    override fun onStart() {
        super.onStart()
        mainActivity = activity as MainActivity
    }

    override fun onResume() {
        super.onResume()
        mainActivity.showMenuActBar(getString(R.string.contacts_app_bar_title), mainActivity)
        messageBtn.setOnClickListener { openWriteMessageActivity() }
        phoneBtn.setOnClickListener { openPhoneActivity() }
        vkBtn.setOnClickListener { openVkgroup() }
        instagrammBtn.setOnClickListener { openInstagrammGroup() }
    }

    private fun openWriteMessageActivity(){
        val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "info@hillparktut.ru", null))
        startActivity(emailIntent)
    }

    private fun openPhoneActivity(){
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:+74742370048")
        startActivity(intent)
    }

    private fun openVkgroup(){
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse("https://vk.com/b_kuzminka")
        startActivity(i)
    }

    private fun openInstagrammGroup(){
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse("https://www.instagram.com/hillparktut/")
        startActivity(i)
    }
}