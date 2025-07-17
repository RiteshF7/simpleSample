package com.trex.simplesample.ui.topheadlines.topHeadlinesPaging

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
    Box(modifier = Modifier.fillMaxSize()) {
        ArticleList(articles, onNewsClick)

        when {
            articles.loadState.refresh is LoadState.Loading -> {
                ShowLoading()
            }

            articles.loadState.refresh is LoadState.Error -> {
                val error = articles.loadState.refresh as LoadState.Error
                ShowError(error.error.localizedMessage ?: "Unknown error")
            }
        }

        when {
            articles.loadState.append is LoadState.Loading -> {
                Box(
                    modifier = Modifier.align(Alignment.BottomCenter)
                ) {
                    ShowLoading()
                }
            }

            articles.loadState.append is LoadState.Error -> {
                val error = articles.loadState.append as LoadState.Error
                Box(
                    modifier = Modifier.align(Alignment.BottomCenter)
                ) {
                    ShowError(error.error.localizedMessage ?: "Unknown error")
                }
            }
        }
    }
}

@Composable
fun ArticleList(articles: LazyPagingItems<Article>, onNewsClick: (url: String) -> Unit) {
    LazyColumn {
        items(
            count = articles.itemCount,
            key = { index -> articles.peek(index)?.url ?: index }
        ) { index ->
            articles[index]?.let { article ->
                Article(article, onNewsClick)
            }
        }
    }
}