package com.codeofmario.blogapp.domain.di

import com.codeofmario.blogapp.domain.repository.auth.AuthRepository
import com.codeofmario.blogapp.domain.repository.auth.AuthRepositoryImpl
import com.codeofmario.blogapp.domain.repository.post.PostRepository
import com.codeofmario.blogapp.domain.repository.post.PostRepositoryImpl
import com.codeofmario.blogapp.domain.usecase.auth.AuthUseCase
import com.codeofmario.blogapp.domain.usecase.auth.AuthUseCaseImpl
import com.codeofmario.blogapp.domain.usecase.post.PostUseCase
import com.codeofmario.blogapp.domain.usecase.post.PostUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {

    // REPOSITORIES
    @Binds
    fun bindAuthRepository(
        repository: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    fun bindPostRepository(
        repository: PostRepositoryImpl
    ): PostRepository

    // USE CASES
    @Binds
    abstract fun bindAuthUseCase(
        useCase: AuthUseCaseImpl
    ): AuthUseCase

    @Binds
    abstract fun bindPostUseCase(
        useCase: PostUseCaseImpl
    ): PostUseCase
}