package kz.rza383.domain.repository

import kz.rza383.auaray.network.CurrentWeather
import kz.rza383.auaray.network.ForecastResponse
import kz.rza383.domain.entity.Result

interface MyRepository  {

    suspend fun getCurrentWeather(
        latitude: Float,
        longitude: Float,
        uvIndex: String,
        precipitationChance: String,
        isCurrentWeather: Boolean,
        forecastDays: String,
        auto: String
    ): Result<CurrentWeather>

    suspend fun getForecast(latitude: Float,
                            longitude: Float,
                            dailyParams: Array<String>,
                            forecastDays: String,
                            auto: String): Result<ForecastResponse>
}