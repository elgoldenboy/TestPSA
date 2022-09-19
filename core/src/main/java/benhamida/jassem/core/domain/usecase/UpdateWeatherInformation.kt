package benhamida.jassem.core.domain.usecase

import benhamida.jassem.core.domain.model.City
import benhamida.jassem.core.domain.model.Weather
import benhamida.jassem.core.domain.repository.WeatherRepository

class UpdateWeatherInformation(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke(city: City, weather: Weather) = weatherRepository.updateWeatherData(city, weather)
}