package com.trex.simplesample.ui.topheadlines.topHeadlinesoffline


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.trex.simplesample.domain.models.Article
import com.trex.simplesample.ui.base.Article

@Composable
fun TopHeadlinesOfflineScreenRoute(
    onNewsClick: (url: String) -> Unit,
    topHeadlineViewModel: TopHeadlinesOfflineViewmodel = hiltViewModel()
) {
    val topHeadlineUiState = topHeadlineViewModel.topHeadlinesFlow.collectAsLazyPagingItems()

    Column(modifier = Modifier.padding(4.dp)) {
        TopHeadlineScreen(topHeadlineUiState, onNewsClick)
    }
}

@Composable
fun TopHeadlineScreen(
    articles: LazyPagingItems<Article>,
    onNewsClick: (url: String) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        ArticleList(articles, onNewsClick)
    }
}

@Composable
fun ArticleList(articles: LazyPagingItems<Article>, onNewsClick: (url: String) -> Unit) {
    LazyColumn {
        items(
            count = articles.itemCount,
            key = { index -> articles.peek(index)?.url ?: index }
        ) { index ->
            articles.peek(index)?.let { article ->
                Article(article, onNewsClick)
            }
        }
    }
}