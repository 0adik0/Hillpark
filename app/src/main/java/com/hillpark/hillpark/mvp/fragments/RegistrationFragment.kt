package com.hillpark.hillpark.mvp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.google.android.material.snackbar.Snackbar
import com.hillpark.hillparkApp.R
import com.hillpark.hillpark.constants.FragmentNames
import com.hillpark.hillpark.mvp.presenters.RegistrationPresenter
import com.hillpark.hillpark.mvp.view.RegistrationView
import com.hillpark.hillpark.utils.waitUtils
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_registration.*
import ru.avtoapp.partner.base.mvp.BaseActivity

class RegistrationFragment : MvpAppCompatFragment(), RegistrationView {

    @InjectPresenter
    lateinit var presenter: RegistrationPresenter
    private lateinit var loginActivity : BaseActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onStart() {
        super.onStart()
        loginActivity = activity as BaseActivity
        registerBtn.setOnClickListener { register() }
    }

    private fun register(){
        if((regCodeEditText.text.toString().length != 8 ||
                emailInputEditText.text.toString() == "" ||
                passInputEditText.text.toString() == "" ||
                repeatPassInputEditText.text.toString() == "") ||
                (passInputEditText.text.toString()!=repeatPassInputEditText.text.toString()) || (!checkBoxPolicy.isChecked || !checkBoxRules.isChecked || !checkBoxData.isChecked)
        ){
            showSnackBar(getString(R.string.register_error_fields))
            if((passInputEditText.text.toString()!=repeatPassInputEditText.text.toString()))showSnackBar(getString(R.string.register_error_pass))
        }else{
            presenter.register(
                regCodeEditText.text.toString(),
                emailInputEditText.text.toString(),
                phoneInputEditText.text.toString(),
                passInputEditText.text.toString(),
                repeatPassInputEditText.text.toString(),
                nameInputEditText.text.toString(),
                lastNameInputEditText.text.toString()
            )
        }
    }

    override fun showSnackBar(message: String) {
        Snackbar.make(root, message, Snackbar.LENGTH_SHORT)
            .show()
    }

    override fun finishRegister() {
        waitUtils.wait1sec().subscribeBy(
            onComplete = {loginActivity.setFragment(FragmentNames.LoginFragment)},
            onError = {e -> e.printStackTrace()}
        )
    }
}