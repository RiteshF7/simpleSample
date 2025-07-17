package com.trex.simplesample.ui.base


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.trex.simplesample.R
import com.trex.simplesample.domain.models.Article


@Composable
fun TitleText(title: String) {
    Text(
        text = title,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    )
}


@Composable
fun DescriptionText(description: String?) {
    Text(
        overflow = TextOverflow.Ellipsis,
        text = description ?: "",
        fontSize = 14.sp,
        color = Color.DarkGray
    )
}

@Composable
fun ImageViewFromUrl(
    imageUrl: String?,
    modifier: Modifier = Modifier,
) {

    AsyncImage(
        model = imageUrl,
        contentDescription = "Image from URL",
        contentScale = ContentScale.FillWidth,
        modifier = modifier
            .height(200.dp)
            .fillMaxWidth()
    )

}

@Composable
fun ShowLoading() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val contentDesc = stringResource(R.string.loading)
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center)
                .semantics {
                    contentDescription = contentDesc
                })
    }
}

@Composable
fun ShowError(text: String, retryClicked: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_cancel),
            tint = Color.Red,
            contentDescription = null,
            modifier = Modifier
                .width(120.dp)
                .height(120.dp)
        )
        Text(
            text = stringResource(R.string.error_title),
            style = MaterialTheme.typography.titleMedium,
            color = Color.Red,
            fontSize = 18.sp,
            modifier = Modifier
                .padding(4.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Gray,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(4.dp)
        )

        Button(
            onClick = { retryClicked() },
            shape = RoundedCornerShape(0.dp),
            modifier = Modifier
                .padding(top = 20.dp)
                .height(50.dp)
                .width(140.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red
            )
        ) {
            Text(text = stringResource(R.string.try_again))
        }
    }
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
fun ArticleList(articles: List<Article>, onNewsClick: (url: String) -> Unit) {
    LazyColumn {
        items(articles.size) { index ->
            ArticleItem(article = articles[index], onNewsClick = onNewsClick)
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



