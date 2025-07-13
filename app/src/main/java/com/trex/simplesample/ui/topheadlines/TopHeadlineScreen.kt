package com.trex.simplesample.ui.topheadlines

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.trex.simplesample.domain.models.Article
import com.trex.simplesample.ui.base.ArticleList
import com.trex.simplesample.ui.base.ShowError
import com.trex.simplesample.ui.base.ShowLoading
import com.trex.simplesample.ui.base.UiState

@Composable
fun TopHeadlineRoute(
    onNewsClick: (url: String) -> Unit,
    topHeadlineViewModel: TopHeadlineViewModel = hiltViewModel()
) {

    val topHeadlineUiState: UiState<List<Article>> by topHeadlineViewModel.topHeadlineUiState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.padding(4.dp)) {
        TopHeadlineScreen(topHeadlineUiState, onNewsClick, onRetryClick = {
            topHeadlineViewModel.startFetchingArticles()
        })
    }
}

@Composable
fun TopHeadlineScreen(
    uiState: UiState<List<Article>>,
    onNewsClick: (url: String) -> Unit,
    onRetryClick: () -> Unit
) {
    when (uiState) {
        is UiState.Success -> {
            ArticleList(uiState.data, onNewsClick)
        }

        is UiState.Loading -> {
            ShowLoading()
        }

        is UiState.Error -> {
            ShowError(text = uiState.message) {
                onRetryClick()
            }
        }
    }
}