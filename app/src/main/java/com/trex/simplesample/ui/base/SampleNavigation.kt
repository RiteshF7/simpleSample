package com.trex.simplesample.ui.base

import android.content.Context
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.trex.simplesample.ui.country.CountryListRoute
import com.trex.simplesample.ui.home.HomeScreenRoute
import com.trex.simplesample.ui.language.LanguageListRoute
import com.trex.simplesample.ui.news.NewsListRoute
import com.trex.simplesample.ui.source.NewsSourceRoute
import com.trex.simplesample.ui.search.SearchScreenRoute
import com.trex.simplesample.ui.topheadlines.TopHeadlineRoute
import com.trex.simplesample.ui.topheadlines.topHeadlinesPaging.PaginationTopHeadlineRoute
import com.trex.simplesample.ui.topheadlines.topHeadlinesoffline.TopHeadlinesOfflineScreenRoute
import com.trex.simplesample.utils.AppConstants

sealed class Route(val name: String) {

    object HomeScreen : Route("homescreen")

    object TopHeadline : Route("topheadline")
    object PaginationTopHeadline : Route("paginationtopheadline")

    object OfflineTopHeadline : Route("offlinetopheadline")
    object NewsSources : Route("newssources")
    object LanguageList : Route("languagelist")
    object CountryList : Route("countrylist")
    object Search : Route("search")

    object NewsList :
        Route(name = "newslist?sourceId={sourceId}&countryId={countryId}&languageId={languageId}") {
        fun passData(
            sourceId: String = "",
            countryId: String = "",
            languageId: String = ""
        ): String {
            return "newslist?sourceId=$sourceId&countryId=$countryId&languageId=$languageId"
        }
    }

}


@Composable
fun SampleNavHost() {

    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = Route.HomeScreen.name
    ) {
        composable(route = Route.HomeScreen.name) {
            HomeScreenRoute(navController)
        }

        composable(route = Route.TopHeadline.name) {
            TopHeadlineRoute(onNewsClick = {
                openCustomChromeTab(context, it)
            })
        }
        composable(route = Route.NewsSources.name) {
            NewsSourceRoute(onNewsClick = {
                navController.navigate(route = Route.NewsList.passData(sourceId = it))
            })
        }

        composable(route = Route.CountryList.name) {
            CountryListRoute(onCountryClick = {
                navController.navigate(route = Route.NewsList.passData(countryId = it))
            })
        }


        composable(route = Route.LanguageList.name) {
            LanguageListRoute(onLanguageClick = {
                navController.navigate(route = Route.NewsList.passData(languageId = it))
            })
        }

        composable(route = Route.PaginationTopHeadline.name) {
            PaginationTopHeadlineRoute(onNewsClick = {
                openCustomChromeTab(context, it)
            })
        }

        composable(route = Route.Search.name) {
            SearchScreenRoute(onNewsClick = {
                openCustomChromeTab(context, it)
            })
        }

        composable(route = Route.OfflineTopHeadline.name) {
            TopHeadlinesOfflineScreenRoute(onNewsClick = {
                openCustomChromeTab(context, it)
            })
        }

        composable(
            route = Route.NewsList.name,
            arguments = listOf(
                navArgument(AppConstants.SOURCE_ID) {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument(AppConstants.COUNTRY_ID) {
                    type = NavType.StringType
                    defaultValue = ""

                },
                navArgument(AppConstants.LANGUAGE_ID) {
                    type = NavType.StringType
                    defaultValue = ""

                }
            )
        ) { it ->
            val sourceId = it.arguments?.getString(AppConstants.SOURCE_ID).toString()
            val countryId = it.arguments?.getString(AppConstants.COUNTRY_ID).toString()
            val languageId = it.arguments?.getString(AppConstants.LANGUAGE_ID).toString()

            NewsListRoute(onNewsClick = {
                openCustomChromeTab(context, it)
            }, sourceId = sourceId, countryId = countryId, languageId = languageId)
        }


    }
}

fun openCustomChromeTab(context: Context, url: String) {
    val builder = CustomTabsIntent.Builder()
    val customTabsIntent = builder.build()
    customTabsIntent.launchUrl(context, url.toUri())
}