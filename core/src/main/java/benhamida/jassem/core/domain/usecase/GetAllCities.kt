package benhamida.jassem.core.domain.usecase

import benhamida.jassem.core.domain.repository.CityRepository

class GetAllCities(private val cityRepository: CityRepository) {
    suspend operator fun invoke() = cityRepository.getCities()
}