package benhamida.jassem.core.domain.repository

import benhamida.jassem.core.domain.model.City

interface CityRepository {

    suspend fun getCities(): ArrayList<City>
    suspend fun addCity(city: City)
}