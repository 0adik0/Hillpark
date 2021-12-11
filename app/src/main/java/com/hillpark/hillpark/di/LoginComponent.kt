package com.hillpark.hillpark.di

import com.hillpark.hillpark.di.module.LoginModule
import com.hillpark.hillpark.server.LoginApi
import dagger.Component

@EnvScope
@Component(dependencies = [AndroidComponent::class], modules = [LoginModule::class])
interface LoginComponent {
    val loginApi: LoginApi
}