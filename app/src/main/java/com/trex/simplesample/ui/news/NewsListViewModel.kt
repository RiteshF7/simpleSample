package com.trex.simplesample.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trex.simplesample.data.remote.repositories.NewsRepositoryImpl
import com.trex.simplesample.domain.models.Article
import com.trex.simplesample.ui.base.UiState
import com.trex.simplesample.utils.DispatcherProvider
import com.trex.simplesample.utils.NetworkHelper
import com.trex.simplesample.utils.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val newsRepository: NewsRepositoryImpl,
    private val logger: Logger,
    private val dispatcherProvider: DispatcherProvider,
    private val networkHelper: NetworkHelper,
) : ViewModel() {

    private val _newsUiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)

    val newUiState: StateFlow<UiState<List<Article>>> = _newsUiState

    private fun checkInternetConnection(): Boolean = networkHelper.isNetworkConnected()

    fun fetchNewsBySources(sourceId: String) {
        if (checkInternetConnection()) fetchNewsBySourcesByNetwork(sourceId)
        else {
            showNoInternetError()
        }

    }

    private fun fetchNewsBySourcesByNetwork(sourceId: String) {
        viewModelScope.launch(dispatcherProvider.main) {
            newsRepository.getNewsBySource(sourceId)
                .flowOn(dispatcherProvider.io)
                .catch { e ->
                    _newsUiState.value = UiState.Error(e.toString())
                }.collect {
                    _newsUiState.value = UiState.Success(it)
                    logger.d("NewsViewModel", it.toString())
                }
        }
    }


    fun fetchNewsByCountry(countryId: String) {
        if (checkInternetConnection()) fetchNewsByCountryByNetwork(countryId)
        else {
            showNoInternetError()
        }
    }

    private fun showNoInternetError() {
        _newsUiState.value = UiState.Error("No Internet Connection")
    }

    private fun fetchNewsByCountryByNetwork(countryId: String) {
        viewModelScope.launch(dispatcherProvider.main) {
            newsRepository.getNewsByCountry(countryId)
                .flowOn(dispatcherProvider.io)
                .catch { e ->
                    _newsUiState.value = UiState.Error(e.toString())
                }.collect {
                    if (!checkInternetConnection() && it.isEmpty()) {
                        _newsUiState.value = UiState.Error("Data Not found.")
                    } else {
                        _newsUiState.value = UiState.Success(it)
                    }
                }
        }
    }


    fun fetchNewsByLanguage(languageId: String) {
        val separatedValues: List<String> = languageId.split(",")
        val languageIdFirst = separatedValues.getOrNull(0)
        val languageIdSecond = separatedValues.getOrNull(1)

        if (checkInternetConnection()) fetchNewsByLanguageByNetwork(
            languageIdFirst.toString(),
            languageIdSecond.toString()
        )
        else {
            showNoInternetError()
        }
    }

    private fun fetchNewsByLanguageByNetwork(languageIdFirst: String, languageIdSecond: String) {
        viewModelScope.launch(dispatcherProvider.main) {
            newsRepository.getNewsByLanguage(languageIdFirst)
                .zip(newsRepository.getNewsByLanguage(languageIdSecond)) { firstLangRes, secLangRes ->
                    return@zip Pair(firstLangRes, secLangRes)
                }
                .flowOn(dispatcherProvider.io)
                .catch { e ->
                    _newsUiState.value = UiState.Error(e.toString())
                }
        }
    }


}