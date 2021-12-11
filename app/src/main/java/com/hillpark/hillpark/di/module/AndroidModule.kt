package com.hillpark.hillpark.di.module

import android.content.Context
import android.content.res.Resources
import javax.inject.Singleton
import dagger.Module
import dagger.Provides

@Module
class AndroidModule(private val mContext: Context) {

    @Provides
    @Singleton
    internal fun provideContext(): Context {
        return mContext
    }

    @Provides
    @Singleton
    internal fun provideResources(): Resources {
        return mContext.resources
    }
}