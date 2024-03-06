package com.codeofmario.blogapp.data.remote.interceptor

import com.codeofmario.blogapp.domain.repository.auth.AuthRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptorImpl @Inject constructor(
    private val wrappedAuthRepository: dagger.Lazy<AuthRepository>,
): AuthInterceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val authRepository = wrappedAuthRepository.get()

        val request = chain.request()
        val accessToken = runBlocking { authRepository.getAccessToken().first() }

        return chain.proceed(newRequestWithAccessToken(accessToken, request))
    }

    private fun newRequestWithAccessToken(accessToken: String?, request: Request): Request =
        request.newBuilder()
            .header("Authorization", "Bearer $accessToken")
            .build()
}