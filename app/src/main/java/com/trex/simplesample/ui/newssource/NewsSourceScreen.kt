package com.trex.simplesample.ui.newssource

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.trex.simplesample.domain.models.NewsSources
import com.trex.simplesample.ui.base.ShowError
import com.trex.simplesample.ui.base.ShowLoading
import com.trex.simplesample.ui.base.UiState
import com.trex.simplesample.ui.base.theme.purple

@Composable
fun NewsSourceRoute(
    onNewsClick: (source: String) -> Unit,
    newsSourceViewModel: NewsSourceViewModel = hiltViewModel()
) {

    val newsSourceUiState: UiState<List<NewsSources>> by newsSourceViewModel.newsSourceUiState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.padding(4.dp)) {
        TopHeadlineScreen(newsSourceUiState, onNewsClick, onRetryClick = {
            newsSourceViewModel.startFetchingArticles()
        })
    }
}

@Composable
fun TopHeadlineScreen(
    uiState: UiState<List<NewsSources>>,
    onNewsClick: (source: String) -> Unit,
    onRetryClick: () -> Unit
) {
    when (uiState) {
        is UiState.Success -> {
            NewsSourceList(uiState.data, onNewsClick)
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

@Composable
fun NewsSourceList(articles: List<NewsSources>, onNewsClick: (source: String) -> Unit) {
    LazyColumn {
        items(articles.size) { index ->
            NewsSourceItem(articles[index], onNewsClick)
        }
    }
}

@Composable
fun NewsSourceItem(newsSources: NewsSources, onNewsClick: (source: String) -> Unit){
    Button(
        onClick = { onNewsClick(newsSources.sourceId) },
        colors = ButtonDefaults.buttonColors(
            containerColor = purple
        ),
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .padding(start = 20.dp, top = 5.dp, end = 20.dp, bottom = 5.dp)
            .fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    ) {
        Text(
            text = newsSources.name,
            color = Color.White,
            fontSize = 16.sp
        )
    }
}
