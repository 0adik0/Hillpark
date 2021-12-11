package com.hillpark.hillpark.mvp

import android.os.Bundle
import android.view.View
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.hillpark.hillparkApp.R
import com.hillpark.hillpark.constants.FragmentNames
import kotlinx.android.synthetic.main.activity_auth.*
import ru.avtoapp.partner.base.mvp.BaseActivity

class AuthActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        setFragment(FragmentNames.LoginFragment)
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

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount>1) {
            super.onBackPressed()
            appBarAuth.visibility = View.GONE
        }else{
            openExitDialog()
        }
    }

    private fun openExitDialog(){
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.exit_dialog_title)
            .setMessage(R.string.exit_dialog_text)
            .setPositiveButton(R.string.dialog_yes) { _, _ -> finishAffinity()}
            .setNegativeButton(R.string.dialog_no, null)
            .show()
    }
}
