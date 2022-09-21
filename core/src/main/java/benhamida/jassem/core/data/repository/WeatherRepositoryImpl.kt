package benhamida.jassem.core.data.repository

import android.util.Log
import benhamida.jassem.core.data.Constants
import benhamida.jassem.core.data.api.ApiClient
import benhamida.jassem.core.data.api.WeatherApi
import benhamida.jassem.core.data.db.dao.WeatherDAO
import benhamida.jassem.core.data.mapper.toModel
import benhamida.jassem.core.data.mapper.toWeather
import benhamida.jassem.core.data.api.ResultApi
import benhamida.jassem.core.domain.model.City
import benhamida.jassem.core.domain.model.Weather
import benhamida.jassem.core.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val weatherApi: WeatherApi,
    private val weatherDAO: WeatherDAO
) : WeatherRepository {

    private val TAG = WeatherRepositoryImpl::class.java.simpleName

    override suspend fun getWeather(cityId: Long): Weather? {
        return weatherDAO.getWeatherForCity(cityId)?.toWeather()
    }

    override suspend fun updateWeatherData(city: City) {
        when (val resultApi = ApiClient.safeApiCall("Error while fetching weather data") {
            weatherApi.getWeatherInfo(
                city.lat,
                city.lon,
                Constants.API_APP_ID,
                Constants.API_EXCLUDE_PARAM,
                Constants.MEASUREMENT_UNIT
            )
        }) {
            is ResultApi.Success -> {
                val weatherDTO = resultApi.data
                var weather = weatherDTO!!.toWeather(city)
                weatherDAO.getWeatherForCity(city.id).let { weatherModel ->
                    if (weatherModel != null) {
                        weatherDAO.update(
                            weather.toModel().apply {
                                id = weatherModel.id
                                city_id = city.id
                            }
                        )
                    } else {
                        weatherDAO.insert(weather.toModel().apply { city_id = city.id })
                    }
                }
            }
            is ResultApi.Error -> {
                Log.e(TAG, "Error while fetching weather data")
            }
        }
    }


}