package com.hillpark.hillpark.mvp.presenters

import android.content.Context.MODE_PRIVATE
import com.arellomobile.mvp.InjectViewState
import com.hillpark.hillpark.AppClass
import com.hillpark.hillparkApp.R
import com.hillpark.hillpark.constants.AppConst
import com.hillpark.hillpark.model.ProfileModel
import com.hillpark.hillpark.mvp.view.ProfileView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import ru.avtoapp.partner.base.mvp.BasePresenter

@InjectViewState
class ProfilePresenter : BasePresenter<ProfileView>() {
    fun getProfile(){
        viewState.showLoading()
        unsubscribeOnDestroy(
        AppClass.getServerComponent().serverApi.getProfile()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {personResponse ->
                    var ctr = 0

                    while(personResponse.body()!!.packetPerson.valueList[1].structure.name[ctr]!="birthdate")ctr++
                    var birthdate = personResponse.body()!!.packetPerson.valueList[1].structure.personValue[ctr].personAdate.date
                    if(birthdate==null)birthdate=""
                    ctr = 0

                    while(personResponse.body()!!.packetPerson.valueList[1].structure.name[ctr]!="email")ctr++
                    var email = personResponse.body()!!.packetPerson.valueList[1].structure.personValue[ctr].personOptional.name
                    if(email==null)email=""
                    ctr = 0

                    while(personResponse.body()!!.packetPerson.valueList[1].structure.name[ctr]!="first_name")ctr++
                    var first_name = personResponse.body()!!.packetPerson.valueList[1].structure.personValue[ctr].personOptional.name
                    if(first_name==null)first_name=""
                    ctr = 0

                    while(personResponse.body()!!.packetPerson.valueList[1].structure.name[ctr]!="gender")ctr++
                    var isMan = false
                    if(personResponse.body()!!.packetPerson.valueList[1].structure.personValue[ctr].personOptional.name!=null) {
                        val gender =
                            personResponse.body()!!.packetPerson.valueList[1].structure.personValue[ctr].personOptional.name
                        if (gender != null) {
                            if (personResponse.body()!!.packetPerson.valueList[1].structure.personValue[ctr].personOptional.name == "male") {
                                isMan = true
                            }
                        }
                    }
                    ctr = 0

                    while(personResponse.body()!!.packetPerson.valueList[1].structure.name[ctr]!="last_name")ctr++
                    var last_name = personResponse.body()!!.packetPerson.valueList[1].structure.personValue[ctr].personOptional.name
                    if(last_name==null)last_name=""
                    ctr = 0

                    while(personResponse.body()!!.packetPerson.valueList[1].structure.name[ctr]!="middle_name")ctr++
                    var middle_name = personResponse.body()!!.packetPerson.valueList[1].structure.personValue[ctr].personOptional.name
                    if(middle_name==null)middle_name=""
                    ctr = 0

                    while(personResponse.body()!!.packetPerson.valueList[1].structure.name[ctr]!="phone")ctr++
                    var phone = personResponse.body()!!.packetPerson.valueList[1].structure.personValue[ctr].personOptional.name
                    if(phone==null)phone=""

                    val profile = ProfileModel(
                        firstName = first_name,
                        midName = middle_name,
                        lastName = last_name,
                        date = birthdate,
                        genderMale = true,
                        phone = phone,
                        email = email
                    )
                    viewState.hideLoading()
                    viewState.showProfileData(profile)
                },
                onError = {e -> e.printStackTrace(); viewState.hideLoading()}
            )
        )
    }

    fun updateProfile(last_name: String, first_name: String, middle_name: String, birthDate: String, phone: String, gender: String){
        viewState.showLoading()
        val date = "ADate:s:" + birthDate.toString() + "&"
        unsubscribeOnDestroy(
        AppClass.getServerComponent().serverApi.updateProfile("s:" + last_name.toString(),"s:" + first_name.toString(),middle_name = "s:" + middle_name.toString(),birthday = date, gender = "s:" + gender,phone = "s:" + phone.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {personResponse ->
                    if(personResponse.code()==200) {
                        AppClass.getContext().getSharedPreferences(AppConst.APP_PREFERENCES, MODE_PRIVATE).edit().putString(AppConst.FIRST_NAME, first_name).apply()
                        AppClass.getContext().getSharedPreferences(AppConst.APP_PREFERENCES, MODE_PRIVATE).edit().putString(AppConst.LAST_NAME, last_name).apply()
                        viewState.showSnackBar(AppClass.getContext().resources.getString(R.string.profile_mess_update_success))
                    }else{
                        viewState.showSnackBar(AppClass.getContext().resources.getString(R.string.profile_mess_update_fail))
                    }
                    viewState.hideLoading()
                },
                onError = {e -> e.printStackTrace();viewState.hideLoading()}
            )
        )
    }
}