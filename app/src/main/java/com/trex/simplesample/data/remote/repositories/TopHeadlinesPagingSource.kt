package com.trex.simplesample.data.remote.repositories

import android.util.Log
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
) :
    PagingSource<Int, Article>() {

    init {
        println("PagingSource Created: TopHeadlinesPagingSource")
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        println("getRefreshKey called. Anchor = ${state.anchorPosition}")

        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition)
        return page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        println("PagingSource load called for page: ${params.key ?: AppConstants.INITIAL_PAGE}")

        val page = params.key ?: AppConstants.INITIAL_PAGE
        return runCatching {
            val response = networkService.getTopHeadlines(
                country = country,
                page = page,
                pageSize = AppConstants.PAGE_SIZE
            )
            val articleList = response.apiArticles.apiArticleListToDomain(country)

            LoadResult.Page(
                data = articleList,
                prevKey = if (page == AppConstants.INITIAL_PAGE) null else page - 1,
                nextKey = if (articleList.isEmpty()) null else page + 1
            )
        }.getOrElse { throwable -> LoadResult.Error(throwable) }
    }

}