package com.hillpark.hillpark.di.module

import android.os.Build
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.hillpark.hillpark.AppClass
import com.hillpark.hillpark.di.EnvScope
import com.hillpark.hillpark.server.Constants.Companion.CONNECT_TIMEOUT_MILLIS
import com.hillpark.hillpark.server.Constants.Companion.READ_TIMEOUT_MILLIS
import com.hillpark.hillpark.server.LoginApi
import com.hillpark.hillpark.utils.DynClass

import java.security.cert.CertificateException
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory


@Module
class LoginModule {

    @Provides
    @EnvScope
    internal fun provideLoginApi(retrofit: Retrofit): LoginApi {
        return retrofit.create(LoginApi::class.java)
    }

    @Provides
    @EnvScope
    internal fun provideRetrofit(builder: Retrofit.Builder): Retrofit {
        return builder.baseUrl("http://195.34.243.30:8118/").build()    // http://ab1024.computerica.com/ http://195.34.243.30:8118/
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
        httpClientBuilder.sslSocketFactory(sslSocketFactory, trustManager)

        httpClientBuilder.addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .addHeader("Accept-Encoding", "gzip,deflate")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build()
            chain.proceed(request)
        }

        val cookieJar = PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(AppClass.getContext()))
        DynClass.cookieJar = cookieJar
        httpClientBuilder.cookieJar(DynClass.cookieJar)
        httpClientBuilder.addInterceptor(loggingInterceptor)
        return httpClientBuilder.build()
    }

    @Provides
    @EnvScope
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Provides
    @EnvScope
    internal fun provideUnsafeSocketFactory(trustManager: X509TrustManager): SSLSocketFactory {
        val trustAllCerts = arrayOf<TrustManager>(trustManager)

        return if (Build.VERSION.SDK_INT < 20) {
            try {
                val sslContext = SSLContext.getInstance("TLSv1.2")
                sslContext.init(null, trustAllCerts, java.security.SecureRandom())
                sslContext.socketFactory
            } catch (e: Exception) {
                throw RuntimeException(e)
            }

        } else {
            try {
                val sslContext = SSLContext.getInstance("SSL")
                sslContext.init(null, trustAllCerts, java.security.SecureRandom())
                sslContext.socketFactory
            } catch (e: Exception) {
                throw RuntimeException(e)
            }

        }
    }


    @Provides
    @EnvScope
    internal fun provideUnsafeTrustManager(): X509TrustManager {
        return object : X509TrustManager {
            @Throws(CertificateException::class)
            override fun checkClientTrusted(
                chain: Array<java.security.cert.X509Certificate>,
                authType: String
            ) {
            }

            @Throws(CertificateException::class)
            override fun checkServerTrusted(
                chain: Array<java.security.cert.X509Certificate>,
                authType: String
            ) {
            }

            override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                return arrayOf()
            }
        }
    }
}