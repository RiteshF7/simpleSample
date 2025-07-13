package com.trex.simplesample.ui.base

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.trex.simplesample.utils.AppConstants
import androidx.core.net.toUri

sealed class Route(val name: String) {

    data object HomeScreen : Route("homescreen")

    data object NewsList :
        Route(name = "newslist?sourceId={sourceId}") {
        fun passData(
            sourceId: String = "",
        ): String {
            return "newslist?sourceId=$sourceId"
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
//            HomeScreenRoute(navController)
        }

        composable(
            route = Route.NewsList.name,
            arguments = listOf(
                navArgument(AppConstants.SOURCE_ID) {
                    type = NavType.StringType
                    defaultValue = ""
                },

            )
        ) { it ->
            val sourceId = it.arguments?.getString(AppConstants.SOURCE_ID).toString()

//            NewsListRoute(onNewsClick = {
//                openCustomChromeTab(context, it)
//            }, sourceId = sourceId)
        }
    }
}

fun openCustomChromeTab(context: Context, url: String) {
    val builder = CustomTabsIntent.Builder()
    val customTabsIntent = builder.build()
    customTabsIntent.launchUrl(context, url.toUri())
}