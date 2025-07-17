package com.trex.simplesample.ui.topheadlines.topHeadlinesPaging

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.trex.simplesample.ui.base.PagedArticleList

@Composable
fun PaginationTopHeadlineRoute(
    onNewsClick: (url: String) -> Unit,
    topHeadlineViewModel: TopHeadlinesPagingViewmodel = hiltViewModel()
) {
    val topHeadlineUiState = topHeadlineViewModel.topHeadlineUiState.collectAsLazyPagingItems()

    Column(modifier = Modifier.padding(4.dp)) {
        PagedArticleList(pagedArticles = topHeadlineUiState, onNewsClick = onNewsClick)
    }
}
