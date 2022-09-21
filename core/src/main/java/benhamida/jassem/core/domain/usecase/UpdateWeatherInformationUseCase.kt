package benhamida.jassem.core.domain.usecase

import benhamida.jassem.core.domain.model.City
import benhamida.jassem.core.domain.repository.WeatherRepository

class UpdateWeatherInformationUseCase(private val weatherRepository: WeatherRepository) {
    suspend operator fun invoke(city: City) = weatherRepository.updateWeatherData(city)
}