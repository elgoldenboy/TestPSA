package benhamida.jassem.core.domain.repository

import benhamida.jassem.core.domain.model.City
import benhamida.jassem.core.domain.model.Weather

interface WeatherRepository {

    suspend fun getWeather(id: Long): Weather?
    suspend fun updateWeatherData(city: City, weather: Weather)
}