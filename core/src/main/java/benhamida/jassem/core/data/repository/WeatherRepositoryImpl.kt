package benhamida.jassem.core.data.repository

import benhamida.jassem.core.domain.model.City
import benhamida.jassem.core.domain.model.Weather
import benhamida.jassem.core.domain.repository.CityRepository
import benhamida.jassem.core.domain.repository.WeatherRepository

class WeatherRepositoryImpl(): WeatherRepository {

    override suspend fun getWeather(id: Long): Weather? {
        TODO("Not yet implemented")
    }

    override suspend fun updateWeatherData(city: City, weather: Weather) {
        TODO("Not yet implemented")
    }


}