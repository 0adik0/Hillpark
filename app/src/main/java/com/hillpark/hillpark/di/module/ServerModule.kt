package com.hillpark.hillpark.di.module


import com.hillpark.hillpark.di.EnvScope
import com.hillpark.hillpark.server.ServerApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module
class ServerModule {
    @Provides
    @EnvScope
    fun provideServerApi(retrofit: Retrofit): ServerApi {
        return retrofit.create(ServerApi::class.java)
    }
}
