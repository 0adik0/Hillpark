package com.hillpark.hillpark.di.module



import com.hillpark.hillpark.di.EnvScope
import java.security.cert.CertificateException
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor

@Module
class RetrofitSettingsModule {

    @Provides
    @EnvScope
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Provides
    @EnvScope
    fun provideUnsafeSocketFactory(trustManager: X509TrustManager): SSLSocketFactory {
        val trustAllCerts = arrayOf<TrustManager>(trustManager)
        try {
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())
            return sslContext.socketFactory
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }

    @Provides
    @EnvScope
    fun provideUnsafeTrustManager(): X509TrustManager {
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