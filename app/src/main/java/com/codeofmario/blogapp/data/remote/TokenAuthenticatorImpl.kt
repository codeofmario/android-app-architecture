package com.codeofmario.blogapp.data.remote

import com.codeofmario.blogapp.data.remote.dto.response.TokensResponse
import com.codeofmario.blogapp.data.remote.networking.AuthApi
import com.codeofmario.blogapp.domain.repository.auth.AuthRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class TokenAuthenticatorImpl @Inject constructor(
    private val wrappedAuthRepository: dagger.Lazy<AuthRepository>,
    ) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        return runBlocking {
            when(val tokens = getTokens()) {
                is TokensResponse ->  {
                    response.request.newBuilder()
                        .header("Authorization", "Bearer ${tokens.accessToken}")
                        .build()
                }
                else -> null
            }
        }
    }

    private suspend fun getTokens(): TokensResponse? {
        val authRepository = wrappedAuthRepository.get()

        return try {
            val refreshToken = authRepository.getRefreshToken().first()

            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

            val retrofit = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            val service = retrofit.create(AuthApi::class.java)
            val tokens = service.refresh("Bearer $refreshToken").data
            authRepository.saveAccessToken(tokens.accessToken)
            authRepository.saveRefreshToken(tokens.refreshToken)
            return tokens
        } catch(e: Exception) {
            authRepository.removeAccessToken()
            authRepository.removeRefreshToken()
            null
        }
    }
}