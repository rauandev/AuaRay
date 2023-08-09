package kz.rza383.auaray.data.repository

import android.app.Application
import dagger.Lazy
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kz.rza383.auaray.R
import kz.rza383.auaray.network.CurrentWeather
import kz.rza383.auaray.network.ForecastResponse
import kz.rza383.auaray.di.IoDispatcher
import kz.rza383.auaray.network.CurrentWeatherApiService
import kz.rza383.domain.entity.Result
import kz.rza383.domain.repository.MyRepository
import javax.inject.Inject

class MyRepositoryImpl @Inject constructor(
    private val api: Lazy<CurrentWeatherApiService>,
    private val appContext: Application,
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher,
    private val apiCaller: ApiCaller
): MyRepository {

    val chartDescription =
        appContext.resources.getString(
            R.string.chart_description)
    override suspend fun getCurrentWeather(
        latitude: Float,
        longitude: Float,
        uvIndex: String,
        precipitationChance: String,
        isCurrentWeather: Boolean,
        forecastDays: String,
        auto: String
    ): Result<CurrentWeather> =
        withContext(ioDispatcher){
                val response = api
                .get()
                .getCurrentWeather(
                    latitude,
                    longitude,
                    uvIndex,
                    precipitationChance,
                    isCurrentWeather,
                    forecastDays,
                    auto
                )
            return@withContext apiCaller.call(response)
        }


    override suspend fun getForecast(
        latitude: Float,
        longitude: Float,
        dailyParams: Array<String>,
        forecastDays: String,
        auto: String
    ): Result<ForecastResponse> =
        withContext(ioDispatcher){
            val response = api
                .get()
                .getForecast(
                    latitude,
                    longitude,
                    dailyParams,
                    forecastDays,
                    auto
                )
            return@withContext apiCaller.call(response)
        }


}