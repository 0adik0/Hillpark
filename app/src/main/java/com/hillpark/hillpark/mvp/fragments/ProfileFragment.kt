package com.hillpark.hillpark.mvp.fragments

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.hillpark.hillpark.AppClass
import com.hillpark.hillparkApp.R
import com.hillpark.hillpark.model.ProfileModel
import com.hillpark.hillpark.mvp.MainActivity
import com.hillpark.hillpark.mvp.presenters.ProfilePresenter
import com.hillpark.hillpark.mvp.view.ProfileView
import com.hillpark.hillpark.utils.Utils
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.item_calendar.*

class ProfileFragment : MvpAppCompatFragment(), ProfileView {

    @InjectPresenter
    lateinit var presenter: ProfilePresenter
    private lateinit var mainActivity: MainActivity

    private lateinit var dialog: AlertDialog
    private var month: Int = 0
    private var year: Int = 0
    private var day: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onStart() {
        super.onStart()
        mainActivity = activity as MainActivity
        birthDayEditText
        emailInputEditText
        phoneInputEditText
        presenter.getProfile()
        cancelBtn.setOnClickListener { mainActivity.onBackPressed() }
        saveBtn.setOnClickListener { updateProfile() }
    }

    override fun onResume() {
        super.onResume()
        mainActivity.showMenuActBar(getString(R.string.profile_app_bar_title), mainActivity)
        birthDayEditText.setOnFocusChangeListener { v, hasFocus ->  if(hasFocus)showCalendar()}
        birthDayEditText.setKeyListener(null)
    }

    private fun showCalendar(){
        val colorDrawable = ColorDrawable(AppClass.getContext().resources.getColor(R.color.colorPrimary))
        dialog = MaterialAlertDialogBuilder(context)
            .setView(activity?.layoutInflater?.inflate(R.layout.item_calendar, null))
            .setBackground(colorDrawable)
            .setPositiveButton(R.string.dialog_ok) { _, _ -> setDateToBirthDay()}
            .setNegativeButton(R.string.cancel) { _, _ -> }
            .create()
        dialog.show()
        dialog.calendar.setOnDateChangeListener { view, year, month, day ->
            this.year = year
            this.month = month
            this.day = day
        }
    }

    private fun setDateToBirthDay(){
        Log.e("date", day.toString() + " " + month.toString() + " " + year.toString())
        if(day<10){
            birthDayEditText.setText("0" + day.toString() + " " + Utils.getMonth(month) + ", " + year.toString(), TextView.BufferType.EDITABLE)
        }else{
            birthDayEditText.setText(day.toString() + " " + Utils.getMonth(month) + ", " + year.toString(), TextView.BufferType.EDITABLE)
        }
    }

    private fun updateProfile(){
        val textfromEditBirthDay = birthDayEditText.text.toString()
        val  birthday : String = textfromEditBirthDay[textfromEditBirthDay.length-4].toString() +
                                 textfromEditBirthDay[textfromEditBirthDay.length-3].toString() +
                                 textfromEditBirthDay[textfromEditBirthDay.length-2].toString() +
                                 textfromEditBirthDay[textfromEditBirthDay.length-1].toString() +
                                 Utils.getMontForRequest(textfromEditBirthDay) +
                                 textfromEditBirthDay[0].toString() +
                                 textfromEditBirthDay[1].toString()

        var gender = "male"
        if(genderRg.checkedRadioButtonId==R.id.femaleButton)gender = "female"
        presenter.updateProfile(middleNameInputEditText.text.toString(), nameInputEditText.text.toString(), fatherNameInputEditText.text.toString(), birthday, phoneInputEditText.text.toString(), gender.toString())
    }

    override fun showProfileData(profile: ProfileModel) {
        nameInputEditText.setText(profile.firstName, TextView.BufferType.EDITABLE)
        middleNameInputEditText.setText(profile.lastName, TextView.BufferType.EDITABLE)
        fatherNameInputEditText.setText(profile.midName, TextView.BufferType.EDITABLE)
        phoneInputEditText.setText(profile.phone, TextView.BufferType.EDITABLE)
        emailInputEditText.setText(profile.email, TextView.BufferType.EDITABLE)
        Utils.disableEditText(emailInputEditText)
        if(profile.genderMale){
            genderRg.check(maleButton.id)
        }
        if(profile.date.length==8){
            birthDayEditText.setText(
                profile.date[6].toString() + profile.date[7].toString() + " " + Utils.getMonthForApi(profile.date[4].toString() + profile.date[5].toString()) + ", " + profile.date[0].toString() + profile.date[1].toString() + profile.date[2].toString() + profile.date[3].toString() ,
                TextView.BufferType.EDITABLE
            )
        }
    }

    override fun showLoading() {
        constraint.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        constraint.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }
    override fun showSnackBar(message: String) {
        Snackbar.make(root,message,Snackbar.LENGTH_SHORT).show()
    }

}