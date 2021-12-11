package com.hillpark.hillpark.di

import com.hillpark.hillpark.di.module.RetrofitModule
import com.hillpark.hillpark.di.module.RetrofitSettingsModule
import com.hillpark.hillpark.di.module.ServerModule
import dagger.Component
import com.hillpark.hillpark.server.ServerApi

@EnvScope
@Component(
    dependencies = [AndroidComponent::class],
    modules = [ServerModule::class, RetrofitSettingsModule::class, RetrofitModule::class]
)
interface ServerComponent {
    val serverApi: ServerApi
}