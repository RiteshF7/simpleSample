package com.trex.simplesample.ui.topheadlines.topHeadlinesoffline


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.trex.simplesample.domain.models.Article
import com.trex.simplesample.ui.base.DescriptionText
import com.trex.simplesample.ui.base.ImageViewFromUrl
import com.trex.simplesample.ui.base.ShowError
import com.trex.simplesample.ui.base.ShowLoading
import com.trex.simplesample.ui.base.TitleText

@Composable
fun TopHeadlinesOfflineScreenRoute(
    onNewsClick: (url: String) -> Unit,
    topHeadlineViewModel: TopHeadlinesOfflineViewmodel = hiltViewModel()
) {
    val pagingData = topHeadlineViewModel.topHeadlinesFlow.collectAsLazyPagingItems()
    PagedArticleList(pagingData, onNewsClick)
}

@Composable
fun PagedArticleList(pagedArticles: LazyPagingItems<Article>, onNewsClick: (url: String) -> Unit) {

    Column(Modifier.fillMaxWidth()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(28.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(pagedArticles.itemCount) { articleIndex ->
                val article = pagedArticles[articleIndex]
                if (article != null) ArticleItem(article, onNewsClick)
            }

            item {
                when {
                    pagedArticles.loadState.append is LoadState.Loading -> {
                        ShowLoading()
                    }

                    pagedArticles.loadState.append is LoadState.Error -> {
                        ShowError("Something went wrong. Please try again.")
                    }

                }
            }
        }
    }

}

@Composable
fun ArticleItem(
    article: Article,
    onNewsClick: (url: String) -> Unit = {}
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .clickable { onNewsClick(article.url) }
    ) {
        ImageViewFromUrl(article.imageUrl)
        Column(
            Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        ) {


            Spacer(modifier = Modifier.height(8.dp))
            TitleText(article.title)

            Spacer(modifier = Modifier.height(4.dp))
            DescriptionText(article.description)
        }
    }

}

