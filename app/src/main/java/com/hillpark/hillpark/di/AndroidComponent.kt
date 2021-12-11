package com.hillpark.hillpark.di

import android.content.Context
import android.content.res.Resources
import com.hillpark.hillpark.di.module.AndroidModule
import javax.inject.Singleton
import dagger.Component

@Singleton
@Component(modules = [AndroidModule::class])
interface AndroidComponent {
    val context: Context
    val resources: Resources
}
