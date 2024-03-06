package com.codeofmario.blogapp.data.local.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.codeofmario.blogapp.data.local.source.auth.LocalAuthDataSource
import com.codeofmario.blogapp.data.local.source.auth.LocalAuthDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore("preferences")

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun dataStore(@ApplicationContext appContext: Context): DataStore<Preferences> =
        appContext.dataStore

    @Module
    @InstallIn(SingletonComponent::class)
    interface LocalDataSourceModule {
        // DATA SOURCES
        @Binds
        fun bindAuthDataSource(localAuthDataSourceImpl: LocalAuthDataSourceImpl): LocalAuthDataSource
    }
}