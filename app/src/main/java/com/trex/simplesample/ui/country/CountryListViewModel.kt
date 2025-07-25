package com.trex.simplesample.ui.country

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trex.simplesample.data.remote.repositories.CountryListRepositoryImpl
import com.trex.simplesample.domain.models.CountryDto
import com.trex.simplesample.domain.repositories.CountryRepository
import com.trex.simplesample.ui.base.UiState
import com.trex.simplesample.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryListViewModel @Inject constructor(
    private val countryListRepository: CountryRepository,
    private val dispatcherProvider: DispatcherProvider,
) : ViewModel() {

    private val _countryUiState = MutableStateFlow<UiState<List<CountryDto>>>(UiState.Loading)

    val countryUiState: StateFlow<UiState<List<CountryDto>>> = _countryUiState

    init {
        fetchCountry()
    }

    private fun fetchCountry() {
        viewModelScope.launch(dispatcherProvider.main) {
            countryListRepository.getCountry()
                .flowOn(dispatcherProvider.default)
                .catch { e ->
                    _countryUiState.value = UiState.Error(e.toString())
                }.collect {
                    _countryUiState.value = UiState.Success(it)
                }
        }
    }
}
