package com.francis.weatherap.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.francis.weatherap.core.connectivity.def.ConnectivityMonitor
import com.francis.weatherap.core.connectivity.def.ConnectivityStatus
import com.francis.weatherap.core.types.either.Either
import com.francis.weatherap.core.types.either.throwableOrNull
import com.francis.weatherap.core.types.either.valueOrNull
import com.francis.weatherap.data.UiState
import com.francis.weatherap.data.interactors.weather.GetForecastInteractor
import com.francis.weatherap.data.interactors.weather.GetTodayWeatherInteractor
import com.francis.weatherap.data.interactors.weather.model.Forecast
import com.francis.weatherap.data.interactors.weather.model.TodayWeather
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    connectivityMonitor: ConnectivityMonitor,
    private val getForecastInteractor: GetForecastInteractor,
    private val getTodayWeatherInteractor: GetTodayWeatherInteractor
) : ViewModel() {

    @OptIn(FlowPreview::class)
    val connectivityStatus = connectivityMonitor.connectedStatus
        .debounce(300)
        .stateIn(viewModelScope, SharingStarted.Lazily, ConnectivityStatus.Disconnected)

    private val _todayWeatherState = MutableStateFlow<UiState<TodayWeather>>(UiState.Loading)
    val todayWeatherState: StateFlow<UiState<TodayWeather>> = _todayWeatherState.asStateFlow()

    private val _forecastState = MutableStateFlow<UiState<Forecast>>(UiState.Loading)
    val forecastState: StateFlow<UiState<Forecast>> = _forecastState.asStateFlow()

    fun fetchTodayWeather(params: GetTodayWeatherInteractor.Params) = viewModelScope.launch {
        _todayWeatherState.value = UiState.Loading
        val result = getTodayWeatherInteractor(params)
        _todayWeatherState.value = when (result) {
            is Either.Success -> UiState.Success(result.valueOrNull())
            is Either.Failure -> UiState.Error(result.throwableOrNull()?.message ?: "Unknown error")
        }
    }

    fun getForecast(params: GetForecastInteractor.Params) = viewModelScope.launch {
        _forecastState.value = UiState.Loading
        val result = getForecastInteractor(params)
        _forecastState.value = when (result) {
            is Either.Success -> UiState.Success(result.valueOrNull())
            is Either.Failure -> UiState.Error(result.throwableOrNull()?.message ?: "Unknown error")
        }
    }
}