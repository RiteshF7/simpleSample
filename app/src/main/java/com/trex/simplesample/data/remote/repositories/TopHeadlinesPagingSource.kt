package com.trex.simplesample.data.remote.repositories

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.trex.simplesample.data.remote.NewsNetworkService
import com.trex.simplesample.data.remote.models.topheadlines.apiArticleListToDomain
import com.trex.simplesample.di.DefaultCountryName
import com.trex.simplesample.domain.models.Article
import com.trex.simplesample.utils.AppConstants
import dagger.hilt.android.scopes.ViewModelScoped
import jakarta.inject.Inject

@ViewModelScoped
class TopHeadlinesPagingSource @Inject constructor(
    private val networkService: NewsNetworkService,
    @DefaultCountryName private val country: String
) : PagingSource<Int, Article>() {

    init {
        println("PagingSource Created: TopHeadlinesPagingSource")
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        println("getRefreshKey called. Anchor = ${state.anchorPosition}")

        return state.anchorPosition?.let { anchorPosition ->
            val page = state.closestPageToPosition(anchorPosition)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: AppConstants.INITIAL_PAGE
        println("🔄 PagingSource load called - Page: $page, LoadSize: ${params.loadSize}, LoadType: ${params::class.simpleName}")

        return try {
            val response = networkService.getTopHeadlines(
                country = country,
                page = page,
                pageSize = AppConstants.PAGE_SIZE // Use fixed PAGE_SIZE for consistency
            )
            val articleList = response.apiArticles?.apiArticleListToDomain(country) ?: emptyList()

            println("📊 API Response - Page: $page, Articles received: ${articleList.size}, Total: ${response.totalResults}")

            val isEndOfPaginationReached =
                articleList.isEmpty() || articleList.size < AppConstants.PAGE_SIZE

            val nextKey = if (isEndOfPaginationReached) null else page + 1
            val prevKey = if (page == AppConstants.INITIAL_PAGE) null else page - 1

            println("🔑 Keys - PrevKey: $prevKey, NextKey: $nextKey, EndReached: $isEndOfPaginationReached")

            LoadResult.Page(
                data = articleList,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (exception: Exception) {
            println("❌ PagingSource error: ${exception.message}")
            exception.printStackTrace()
            LoadResult.Error(exception)
        }
    }
}