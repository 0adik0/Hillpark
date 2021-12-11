package com.hillpark.hillpark.di.module

import android.os.Build
import com.hillpark.hillpark.di.EnvScope
import com.hillpark.hillpark.server.Constants.Companion.CONNECT_TIMEOUT_MILLIS
import com.hillpark.hillpark.server.Constants.Companion.READ_TIMEOUT_MILLIS
import com.hillpark.hillpark.utils.DynClass

import java.util.concurrent.TimeUnit

import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory


@Module
class RetrofitModule {

    @Provides
    @EnvScope
    internal fun provideRetrofit(builder: Retrofit.Builder): Retrofit {
        return builder.baseUrl("http://195.34.243.30:8118/").build()   // http://ab1024.computerica.com/ http://195.34.243.30:8118/
    }

    @Provides
    @EnvScope
    internal fun provideRetrofitBuilder(client: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    }

    @Provides
    @EnvScope
    internal fun provideOkHttpClient(
        sslSocketFactory: SSLSocketFactory,
        trustManager: X509TrustManager,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.connectTimeout(CONNECT_TIMEOUT_MILLIS.toLong(), TimeUnit.MINUTES)
        httpClientBuilder.readTimeout(READ_TIMEOUT_MILLIS.toLong(), TimeUnit.MINUTES)

        if (Build.VERSION.SDK_INT < 20) {
            try {
                val sslContext = SSLContext.getInstance("TLSv1.2")
                sslContext.init(null, null, java.security.SecureRandom())
                httpClientBuilder.sslSocketFactory(sslContext.socketFactory, trustManager)
            } catch (e: Exception) {
                throw RuntimeException(e)
            }

        } else {
            httpClientBuilder.sslSocketFactory(sslSocketFactory, trustManager)
        }

        httpClientBuilder.cookieJar(DynClass.cookieJar)
        httpClientBuilder.addInterceptor(loggingInterceptor)
        return httpClientBuilder.build()
    }
}