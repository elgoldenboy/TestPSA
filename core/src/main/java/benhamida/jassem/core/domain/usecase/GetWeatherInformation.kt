package benhamida.jassem.core.domain.usecase

import benhamida.jassem.core.domain.repository.WeatherRepository

class GetWeatherInformation(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke(id:Long) = weatherRepository.getWeather(id)
}