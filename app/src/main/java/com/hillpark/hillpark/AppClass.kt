package com.hillpark.hillpark

import android.app.Application
import android.content.Context
import android.util.Log
import com.hillpark.hillpark.di.*
import com.hillpark.hillpark.di.module.AndroidModule
import com.hillpark.hillpark.di.module.LoginModule
import com.hillpark.hillpark.di.module.ServerModule

class AppClass : Application(){
    companion object {
        private lateinit var sAndroidComponent: AndroidComponent
        private lateinit var sLoginComponent: LoginComponent
        private lateinit var sServerComponent: ServerComponent
        fun getContext(): Context {
            return sAndroidComponent.context
        }

        fun getAndroidComponent(): AndroidComponent {
            return sAndroidComponent
        }

        fun getLoginComponent(): LoginComponent {
            return sLoginComponent
        }

        fun getServerComponent(): ServerComponent {
            return sServerComponent
        }
    }

    override fun onCreate() {
        super.onCreate()
        sAndroidComponent = DaggerAndroidComponent.builder()
            .androidModule(AndroidModule(this))
            .build()
        sLoginComponent = DaggerLoginComponent.builder()
            .loginModule(LoginModule())
            .androidComponent(sAndroidComponent)
            .build()
        sServerComponent = DaggerServerComponent.builder()
            .serverModule(ServerModule())
            .androidComponent(sAndroidComponent)
            .build()

        Log.e("inti","lized")
    }

}