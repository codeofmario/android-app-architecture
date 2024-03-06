package com.codeofmario.blogapp.data.local.source.auth

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal val KEY_ACCESS_TOKEN = stringPreferencesKey("key_access_token")
internal val KEY_REFRESH_TOKEN = stringPreferencesKey("key_refresh_token")

class LocalAuthDataSourceImpl @Inject constructor(private val dataStore: DataStore<Preferences>) :
    LocalAuthDataSource {

    override fun getAccessToken(): Flow<String?> {
        return dataStore.data.map {
            it[KEY_ACCESS_TOKEN]
        }
    }

    override suspend fun saveAccessToken(token: String) {
        dataStore.edit {
            it[KEY_ACCESS_TOKEN] = token
        }
    }

    override suspend fun removeAccessToken() {
        dataStore.edit {
            it.remove(KEY_ACCESS_TOKEN)
        }
    }

    override fun getRefreshToken(): Flow<String?> {
        return dataStore.data.map {
            it[KEY_REFRESH_TOKEN]
        }
    }

    override suspend fun saveRefreshToken(token: String) {
        dataStore.edit {
            it[KEY_REFRESH_TOKEN] = token
        }
    }

    override suspend fun removeRefreshToken() {
        dataStore.edit {
            it.remove(KEY_REFRESH_TOKEN)
        }
    }
}