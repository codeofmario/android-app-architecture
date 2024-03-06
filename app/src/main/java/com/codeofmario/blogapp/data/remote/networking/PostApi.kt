package com.codeofmario.blogapp.data.remote.networking

import com.codeofmario.blogapp.data.remote.dto.response.*
import retrofit2.http.*

interface PostApi {
    @GET("posts/")
    suspend fun allPaginated(
        @Query("search") search: String,
        @Query("size") size: Int,
        @Query("page") page: Int,
        @Query("sortBy") sortBy: String?,
        @Query("sortDirection") sortDirection: String?,
        @Query("all") all: Boolean,
    ): PaginatedResponse<List<PostResponse>>

    @GET("posts/{id}")
    suspend fun one(@Path("id") id: String): Response<PostResponse>
}