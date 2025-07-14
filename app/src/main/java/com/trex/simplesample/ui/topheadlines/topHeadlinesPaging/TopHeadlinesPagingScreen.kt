package com.trex.simplesample.ui.topheadlines.topHeadlinesPaging

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.trex.simplesample.domain.models.Article
import com.trex.simplesample.ui.base.Article
import com.trex.simplesample.ui.base.ShowError
import com.trex.simplesample.ui.base.ShowLoading

@Composable
fun PaginationTopHeadlineRoute(
    onNewsClick: (url: String) -> Unit,
    topHeadlineViewModel: TopHeadlinesPagingViewmodel = hiltViewModel()
) {
    val topHeadlineUiState = topHeadlineViewModel.topHeadlineUiState.collectAsLazyPagingItems()

    Column(modifier = Modifier.padding(4.dp)) {
        TopHeadlineScreen(topHeadlineUiState, onNewsClick)
    }
}

@Composable
fun TopHeadlineScreen(articles: LazyPagingItems<Article>, onNewsClick: (url: String) -> Unit) {

    ArticleList(articles, onNewsClick)

    articles.apply {
        when {
            loadState.refresh is LoadState.Loading -> {
                ShowLoading()
            }

            loadState.refresh is LoadState.Error -> {
                val error = articles.loadState.refresh as LoadState.Error
                ShowError(error.error.localizedMessage!!)
            }

            loadState.append is LoadState.Loading -> {
                ShowLoading()
            }

            loadState.append is LoadState.Error -> {
                val error = articles.loadState.append as LoadState.Error
                ShowError(error.error.localizedMessage!!)
            }
        }
    }

}

@Composable
fun ArticleList(articles: LazyPagingItems<Article>, onNewsClick: (url: String) -> Unit) {
    LazyColumn {
        items(articles.itemCount, key = { index -> articles[index]?.url ?: index }) { index ->
            articles[index]?.let {
                Article(it, onNewsClick)
            }
        }
    }
}