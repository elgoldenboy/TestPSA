package benhamida.jassem.core.data.repository

import benhamida.jassem.core.domain.model.City
import benhamida.jassem.core.domain.repository.CityRepository

class CityRepositoryImpl(): CityRepository {

    override suspend fun getCities(): ArrayList<City> {
        TODO("Not yet implemented")
    }

    override suspend fun addCity(city: City) {
        TODO("Not yet implemented")
    }

}