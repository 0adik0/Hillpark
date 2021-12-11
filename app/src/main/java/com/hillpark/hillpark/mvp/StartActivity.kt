package com.hillpark.hillpark.mvp

import android.content.Intent
import android.os.Bundle
import ru.avtoapp.partner.base.mvp.BaseActivity

class StartActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showSnackbar(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}