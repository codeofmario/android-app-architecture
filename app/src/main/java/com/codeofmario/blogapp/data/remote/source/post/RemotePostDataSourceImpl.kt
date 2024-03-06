package com.codeofmario.blogapp.data.remote.source.post

import com.codeofmario.blogapp.data.remote.dto.request.TableFilterRequest
import com.codeofmario.blogapp.data.remote.dto.response.PaginationResponse
import com.codeofmario.blogapp.data.remote.dto.response.PostResponse
import com.codeofmario.blogapp.data.remote.networking.PostApi
import com.codeofmario.blogapp.domain.entity.Pagination
import com.codeofmario.blogapp.domain.entity.Post
import com.codeofmario.blogapp.domain.entity.TableFilter
import javax.inject.Inject

class RemotePostDataSourceImpl @Inject constructor(
    private val api: PostApi,
) : RemotePostDataSource {

    override suspend fun allPaginated(filter: TableFilter): Pair<List<Post>, Pagination> {
        val dto = fromTableFilterToDto(filter)
        val response = api.allPaginated(
            dto.search,
            dto.size,
            dto.page,
            dto.sortBy,
            dto.sortDirection,
            dto.all,
        )
        return Pair(response.data.map { fromDtoToPost(it) }, fromDtoToPagination(response.metadata))
    }

    override suspend fun one(id: String): Post {
        return fromDtoToPost(api.one(id).data)
    }

    private fun fromDtoToPost(dto: PostResponse): Post {
        return Post(
            id = dto.id,
            title = dto.title,
            body = dto.body,
            imageUrl = dto.imageUrl,
        )
    }

    private fun fromDtoToPagination(dto: PaginationResponse): Pagination {
        return Pagination(
            pageNumber = dto.pageNumber,
            totalElements = dto.totalElements,
            totalPages = dto.totalPages,
            size = dto.size,
        )
    }

    private fun fromTableFilterToDto(dto: TableFilter): TableFilterRequest {
        return TableFilterRequest(
            search = dto.search,
            size = dto.size,
            page = dto.page,
            sortBy = dto.sortBy,
            sortDirection = dto.sortDirection,
            all = dto.all,
        )
    }
}