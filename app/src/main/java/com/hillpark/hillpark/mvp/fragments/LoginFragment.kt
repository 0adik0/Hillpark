package com.hillpark.hillpark.mvp.fragments

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.hillpark.hillparkApp.R
import com.hillpark.hillpark.constants.FragmentNames
import com.hillpark.hillpark.mvp.MainActivity
import com.hillpark.hillpark.mvp.presenters.LoginPresenter
import com.hillpark.hillpark.mvp.view.LoginView
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.root
import ru.avtoapp.partner.base.mvp.BaseActivity
import android.view.MotionEvent
import android.view.View.OnTouchListener
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.item_forgot_password.*


class LoginFragment : MvpAppCompatFragment(), LoginView {

    @InjectPresenter
    lateinit var presenter: LoginPresenter
    private lateinit var dialog: AlertDialog
    private lateinit var authActivity : BaseActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onStart() {
        super.onStart()
        authActivity = activity as BaseActivity
        registerBtn.setOnClickListener { authActivity.setFragment(FragmentNames.RegistrationFragment) }
        loginBtn.setOnClickListener {login()}
        passwordInputEditText.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= passwordInputEditText.getRight() - passwordInputEditText.getCompoundDrawables()[2].getBounds().width()) {
                    openForgotDialog()

                    return@OnTouchListener true
                }
            }
            false
        })
        hideLoading()
    }

    private fun login(){
        showLoading()
        loginInputLayout.error = null
        passwordInputLayout.error = null
        val login = loginInputEditText.text.toString()
        val pass = passwordInputEditText.text.toString()
        presenter.login(login, pass)
    }

    private fun openForgotDialog(){
        val colorDrawable = ColorDrawable(context!!.resources.getColor(R.color.colorPrimary))

        dialog = MaterialAlertDialogBuilder(context)
            .setView(this.layoutInflater.inflate(R.layout.item_forgot_password, null))
            .setBackground(colorDrawable)
            .setPositiveButton(R.string.dialog_ok) {_,_ -> presenter.restorePassword(dialog.currentEmailEditText.text.toString())}
            .setNegativeButton(R.string.dialog_cancel) {_,_ ->}
            .create()
        dialog.show()
    }

    override fun showSnackBar(message: String) {
        loginInputLayout.error = getString(R.string.login_err)
        passwordInputLayout.error = getString(R.string.login_err)
        authActivity.showSnackBar(message, root)
    }

    override fun showSnackBarInfo(message: String) {
        authActivity.showSnackBar(message, root)
    }

    override fun showLoading() {
        authActivity.showLoading(progressBar)
    }

    override fun hideLoading() {
        authActivity.hideLoading(progressBar)
    }

    override fun startApp() {
        authActivity.startActivity(Intent(context,MainActivity::class.java))
    }


}