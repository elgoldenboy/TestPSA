package benhamida.jassem.core.domain.usecase

import benhamida.jassem.core.domain.model.City
import benhamida.jassem.core.domain.repository.CityRepository

class AddCity(private val weatherRepository: CityRepository) {
    suspend operator fun invoke(city: City) = weatherRepository.addCity(city)
}