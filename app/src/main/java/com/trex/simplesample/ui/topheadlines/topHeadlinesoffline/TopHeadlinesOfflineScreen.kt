package com.trex.simplesample.ui.topheadlines.topHeadlinesoffline



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.trex.simplesample.data.local.entity.ArticleEntity
import com.trex.simplesample.domain.models.Article

@Composable
fun TopHeadlinesOfflineScreenRoute(
    onNewsClick: (url: String) -> Unit,
    topHeadlineViewModel: TopHeadlinesOfflineViewmodel = hiltViewModel()
) {

    Column(modifier = Modifier.padding(4.dp)) {
        NewsListScreen(topHeadlineViewModel)
    }
}

@Composable
fun NewsListScreen(
    viewModel: TopHeadlinesOfflineViewmodel // Replace with your actual ViewModel
) {
    val pagingData = viewModel.topHeadlinesFlow.collectAsLazyPagingItems()
    val listState = rememberLazyListState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Debug info at top
        DebugInfo(pagingData = pagingData, listState = listState)

        // Main list
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(pagingData.itemCount) { index ->
                pagingData[index]?.let { article ->
                    ArticleItem(
                        article = article,
                        index = index,
                        totalCount = pagingData.itemCount
                    )
                }
            }

            // Loading state at bottom
            item {
                when {
                    pagingData.loadState.append is LoadState.Loading -> {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    pagingData.loadState.append is LoadState.Error -> {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Error loading more items",
                                color = Color.Red
                            )
                        }
                    }
                }
            }
        }
    }

    // Monitor scroll and item access
    LaunchedEffect(listState.isScrollInProgress) {
        if (!listState.isScrollInProgress) {
            val visibleItems = listState.layoutInfo.visibleItemsInfo
            val lastVisibleIndex = visibleItems.lastOrNull()?.index ?: 0
            val firstVisibleIndex = visibleItems.firstOrNull()?.index ?: 0

            println("SCROLL DEBUG:")
            println("  - First visible: $firstVisibleIndex")
            println("  - Last visible: $lastVisibleIndex")
            println("  - Total items: ${pagingData.itemCount}")
            println("  - Distance from end: ${pagingData.itemCount - lastVisibleIndex}")
            println("  - Load state: ${pagingData.loadState.append}")
            println("")
        }
    }
}

@Composable
fun ArticleItem(
    article: Article,
    index: Int,
    totalCount: Int
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp), // Large enough for easy scrolling
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Index indicator
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Item #${index + 1}",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Text(
                    text = "$index / $totalCount",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Article title
            Text(
                text = article.title ?: "No Title",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Article description
            Text(
                text = article.description ?: "No Description",
                fontSize = 14.sp,
                color = Color.Gray,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            // URL
            Text(
                text = article.url ?: "No URL",
                fontSize = 12.sp,
                color = Color.Blue,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            // Distance from end indicator
            val distanceFromEnd = totalCount - index
            if (distanceFromEnd <= 5) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "🔥 $distanceFromEnd items from end",
                    fontSize = 12.sp,
                    color = Color.Red,
                    modifier = Modifier
                        .background(
                            Color.Red.copy(alpha = 0.1f),
                            RoundedCornerShape(4.dp)
                        )
                        .padding(4.dp)
                )
            }
        }
    }
}

@Composable
fun DebugInfo(
    pagingData: LazyPagingItems<Article>,
    listState: LazyListState
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "DEBUG INFO",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Total Items: ${pagingData.itemCount}")
            Text(text = "Load State: ${pagingData.loadState.append}")

            val visibleItems = listState.layoutInfo.visibleItemsInfo
            val lastVisible = visibleItems.lastOrNull()?.index ?: 0

            Text(text = "Last Visible: $lastVisible")
            Text(text = "Distance from End: ${pagingData.itemCount - lastVisible}")

            // Manual trigger button for testing
            Button(
                onClick = {
                    if (pagingData.itemCount > 0) {
                        val nearEndIndex = maxOf(0, pagingData.itemCount - 2)
                        pagingData.get(nearEndIndex)
                        println("Manual trigger: Accessing item $nearEndIndex")
                    }
                },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Manual Trigger Load")
            }
        }
    }
}

// Your ViewModel should look something like this:
/*
@HiltViewModel
class YourViewModel @Inject constructor(
    private val repository: YourRepository
) : ViewModel() {

    val articles = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false,
            prefetchDistance = 2, // Adjust this
            initialLoadSize = 20
        ),
        remoteMediator = TopHeadlinesRemoteMediator(networkService, databaseService),
        pagingSourceFactory = { databaseService.getAllArticles() }
    ).flow.cachedIn(viewModelScope)
}
*/