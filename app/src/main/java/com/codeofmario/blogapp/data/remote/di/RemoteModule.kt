package com.codeofmario.blogapp.data.remote.di

import com.codeofmario.blogapp.BuildConfig
import com.codeofmario.blogapp.data.remote.TokenAuthenticatorImpl
import com.codeofmario.blogapp.data.remote.interceptor.AuthInterceptor
import com.codeofmario.blogapp.data.remote.interceptor.AuthInterceptorImpl
import com.codeofmario.blogapp.data.remote.networking.*
import com.codeofmario.blogapp.data.remote.source.auth.RemoteAuthDataSource
import com.codeofmario.blogapp.data.remote.source.auth.RemoteAuthDataSourceImpl
import com.codeofmario.blogapp.data.remote.source.post.RemotePostDataSource
import com.codeofmario.blogapp.data.remote.source.post.RemotePostDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    // RETROFIT
    @Provides
    fun provideBaseUrl() = "http://10.0.2.2:8000/api/v1/"

    @Singleton
    @Provides
    fun provideOkHttpClient(authInterceptor: AuthInterceptor, authenticator: Authenticator?) = if (BuildConfig.DEBUG){
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .also { client ->
                authenticator?.let {
                    client.authenticator(it)
                }
            }
            .build()
    }else{
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL:String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    // API
    @Provides
    fun provideAuthApi(
        retrofit: Retrofit
    ): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Provides
    fun providePostApi(
        retrofit: Retrofit
    ): PostApi {
        return retrofit.create(PostApi::class.java)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface RemoteDataSourceModule {
        // DATA SOURCES
        @Binds
        fun bindRemoteAuthDataSource(
            service: RemoteAuthDataSourceImpl
        ): RemoteAuthDataSource

        @Binds
        fun bindRemotePostDataSource(
            service: RemotePostDataSourceImpl
        ): RemotePostDataSource

        @Binds
        fun bindAuthInterceptor(
            service: AuthInterceptorImpl
        ): AuthInterceptor

        @Binds
        fun bindTokenAuthenticator(
            service: TokenAuthenticatorImpl
        ): Authenticator
    }
}